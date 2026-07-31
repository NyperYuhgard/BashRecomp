#!/usr/bin/env bash
# Cross-build a self-contained Windows x86-64 exe from Linux (MinGW-w64).
# Builds its own mingw zlib into build-zlib/ on first run (the runtime requires
# ZLIB and the distro sysroot usually lacks a mingw build).
set -euo pipefail
cd "$(dirname "$0")"

if ! command -v x86_64-w64-mingw32-gcc >/dev/null 2>&1; then
    echo "error: mingw-w64 toolchain not found (install gcc-mingw-w64-x86-64)" >&2
    exit 1
fi

ZLIB_PREFIX="${ZLIB_PREFIX:-$(pwd)/build-zlib}"

if [ ! -f "$ZLIB_PREFIX/lib/libz.a" ]; then
    echo "building mingw zlib into $ZLIB_PREFIX ..."
    tmp="$(mktemp -d)"
    trap 'rm -rf "$tmp"' EXIT
    curl -sL -o "$tmp/zlib.tar.gz" \
        https://zlib.net/fossils/zlib-1.3.1.tar.gz
    tar -xzf "$tmp/zlib.tar.gz" -C "$tmp"
    make -C "$tmp/zlib-1.3.1" -f win32/Makefile.gcc \
        PREFIX=x86_64-w64-mingw32- \
        INCLUDE_PATH="$ZLIB_PREFIX/include" \
        LIBRARY_PATH="$ZLIB_PREFIX/lib" \
        BINARY_PATH="$ZLIB_PREFIX/bin" \
        SHARED_MODE=0 -j"$(nproc)" all install
    trap - EXIT
    rm -rf "$tmp"
fi

# Disable the distro sysroot's shared SDL3 package so runtime.cmake fetches its
# pinned static SDL3 (3.4.10). PSX_STATIC_RUNTIME (default ON for MinGW Release)
# then links it statically and the exe stays 100% self-contained (no SDL3.dll).
cmake -S . -B build-win -G Ninja -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_TOOLCHAIN_FILE=cmake/mingw-x86_64.cmake \
    -DPSX_MINGW_EXTRA_PREFIX="$ZLIB_PREFIX" \
    -DCMAKE_DISABLE_FIND_PACKAGE_SDL3=ON \
    -DRECOMP_UI_ENABLE_MODS:BOOL=ON
cmake --build build-win --target psx-runtime

echo
echo "Build complete: build-win/Crash_Bandicoot_Recompiled.exe"
echo "Self-contained (no SDL3.dll needed). Ship it together with the mods/,"
echo "assets/ and bios/ folders that cmake staged next to it, plus your game"
echo "disc (disc/*.cue + *.bin)."

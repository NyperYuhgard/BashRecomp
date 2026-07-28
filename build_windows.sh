#!/bin/bash
# build_windows.sh - Cross-compilación quirúrgica final para Windows

set -e

BUILD_DIR="build-windows"
GENERATOR="Ninja"
BUILD_TYPE="Release"
UI="OFF"

TARGET_TRIPLET="x86_64-w64-mingw32"

# Detectar la carpeta de SDL2 descargada
SDL2_ROOT=$(ls -d $(pwd)/SDL2-2.*/${TARGET_TRIPLET} 2>/dev/null | head -n 1)

if [ -z "$SDL2_ROOT" ]; then
    echo "❌ No se encontró la carpeta de SDL2 MinGW."
    exit 1
fi

echo "=== Compilando para Windows desde Linux ($TARGET_TRIPLET) ==="
echo "=== Usando SDL2 de: $SDL2_ROOT ==="

rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"
cd "$BUILD_DIR"

cmake .. -G "$GENERATOR" \
         -DCMAKE_SYSTEM_NAME=Windows \
         -DCMAKE_SYSTEM_PROCESSOR=x86_64 \
         -DCMAKE_C_COMPILER=${TARGET_TRIPLET}-gcc \
         -DCMAKE_CXX_COMPILER=${TARGET_TRIPLET}-g++ \
         -DCMAKE_RC_COMPILER=${TARGET_TRIPLET}-windres \
         -DCMAKE_FIND_ROOT_PATH="/usr/${TARGET_TRIPLET};$SDL2_ROOT" \
         -DCMAKE_FIND_ROOT_PATH_MODE_PROGRAM=NEVER \
         -DCMAKE_FIND_ROOT_PATH_MODE_LIBRARY=ONLY \
         -DCMAKE_FIND_ROOT_PATH_MODE_INCLUDE=ONLY \
         -DCMAKE_FIND_ROOT_PATH_MODE_PACKAGE=ONLY \
         -DSDL2_FOUND=TRUE \
         -DSDL2_INCLUDE_DIRS="$SDL2_ROOT/include/SDL2" \
         -DSDL2_LIBRARIES="-lmingw32;$SDL2_ROOT/lib/libSDL2main.a;$SDL2_ROOT/lib/libSDL2.dll.a" \
         -DCMAKE_EXE_LINKER_FLAGS="-Wl,-subsystem,console -Wl,-e,mainCRTStartup" \
         -DCMAKE_BUILD_TYPE="$BUILD_TYPE" \
         -DPSX_RECOMP_UI="$UI"

# Compilar
ninja psx-runtime

cd ..

# Copiar la DLL junto al ejecutable
cp "$SDL2_ROOT/bin/SDL2.dll" "$BUILD_DIR/"

echo "✅ ¡Punto de victoria! Binario listo en: $BUILD_DIR/Crash_Bandicoot_Recompiled.exe"

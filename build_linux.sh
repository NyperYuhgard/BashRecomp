#!/usr/bin/env bash
# Build the game natively for Linux.
set -euo pipefail
cd "$(dirname "$0")"

cmake -S . -B build -G Ninja -DCMAKE_BUILD_TYPE=Release \
    -DRECOMP_UI_ENABLE_MODS:BOOL=ON \
    -DPSX_NETPLAY:BOOL=ON \
    -DPSX_MAX_PLAYERS=4

cmake --build build --target psx-runtime
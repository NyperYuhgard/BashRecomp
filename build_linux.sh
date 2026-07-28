#!/bin/bash
# build-linux.sh - Compila para Linux (nativo)

set -e

BUILD_DIR="build-linux"
GENERATOR="Ninja"
BUILD_TYPE="Release"
UI="OFF"

echo "=== Compilando para Linux (nativo) ==="

# Verificar herramientas
command -v cmake >/dev/null 2>&1 || { echo "❌ cmake no instalado"; exit 1; }
command -v ninja >/dev/null 2>&1 || { echo "❌ ninja no instalado"; exit 1; }

mkdir -p "$BUILD_DIR"
cd "$BUILD_DIR"

cmake .. -G "$GENERATOR" \
         -DCMAKE_BUILD_TYPE="$BUILD_TYPE" \
         -DPSX_RECOMP_UI="$UI"

ninja psx-runtime

cd ..

echo "✅ Binario: $BUILD_DIR/psx-runtime"

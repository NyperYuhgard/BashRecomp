# Cross-compilation toolchain: Linux -> Windows x86_64 (MinGW-w64).
# Usage:
#   cmake -S . -B build-win -G Ninja -DCMAKE_BUILD_TYPE=Release \
#         -DCMAKE_TOOLCHAIN_FILE=cmake/mingw-x86_64.cmake \
#         -DRECOMP_UI_ENABLE_MODS:BOOL=ON
#
# Dependencies not present in /usr/x86_64-w64-mingw32 (e.g. a locally built
# zlib) can be made visible with -DPSX_MINGW_EXTRA_PREFIX=/path/to/prefix.

set(CMAKE_SYSTEM_NAME Windows)
set(CMAKE_SYSTEM_PROCESSOR x86_64)

set(CMAKE_C_COMPILER   x86_64-w64-mingw32-gcc)
set(CMAKE_CXX_COMPILER x86_64-w64-mingw32-g++)
set(CMAKE_RC_COMPILER  x86_64-w64-mingw32-windres)
set(CMAKE_ASM_COMPILER x86_64-w64-mingw32-gcc)

set(CMAKE_FIND_ROOT_PATH /usr/x86_64-w64-mingw32)
if(PSX_MINGW_EXTRA_PREFIX)
    list(APPEND CMAKE_FIND_ROOT_PATH ${PSX_MINGW_EXTRA_PREFIX})
endif()

set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_PACKAGE ONLY)

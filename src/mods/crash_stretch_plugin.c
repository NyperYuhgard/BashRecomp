#include "mod_plugins.h"

/*
 * Widescreen Stretch: fill the 16:9 window by stretching the plain 4:3 image
 * instead of widening the field of view (no GTE X-squash). Trade-off: no
 * culling pop-in or edge voids (nothing new is revealed), but everything is
 * uniformly stretched. Complementary to crash.widescreen; enable one or the
 * other in the launcher.
 *
 * Mechanics, reusing runtime-only knobs (no game regen):
 *   - gpu_ws_set_gte_game_mode(1): without a gameplay detector the present
 *     stays 4:3-pillarboxed, which defeats the whole point. Enabling it lets
 *     gameplay frames present to the wide window.
 *   - psx_ws_set_native_wide(0): native-wide pillarboxes canonical frames;
 *     the squash present path is the one that stretches them.
 *   - psx_mod_set_fixed_display_aspect(16, 9): wide window + stretch aspect.
 *   - vblank: the game-entry hook feeds the GTE the wide aspect once; a vblank
 *     callback re-asserts the 4:3 identity so the GTE never squashes. Only the
 *     first gameplay frame is briefly squashed before the vblank corrects it.
 */
extern void gpu_ws_set_gte_game_mode(int on);
extern void psx_ws_set_native_wide(int on);
extern void gte_set_display_aspect(int num, int den);

static void crash_stretch_activate(void) {
    gpu_ws_set_gte_game_mode(1);
    psx_ws_set_native_wide(0);
    (void)psx_mod_set_fixed_display_aspect(16u, 9u);
}

static void crash_stretch_vblank(void) {
    if (psx_mod_game_started())
        gte_set_display_aspect(4, 3);
}

PSX_MOD_CONSTRUCTOR(crash_register_stretch_plugin) {
    (void)psx_mod_register_activation_plugin(
        "crash.stretch", crash_stretch_activate);
    (void)psx_mod_register_vblank_plugin(
        "crash.stretch", crash_stretch_vblank);
}

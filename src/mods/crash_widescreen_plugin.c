#include <string.h>

#include "mod_plugins.h"

/*
 * Crash is a fully 3D title with no sprite-tag helper in its generated code,
 * so the runtime cannot classify frames as gameplay (its widescreen present
 * only engages on gameplay frames; menus/FMV stay 4:3). Two runtime-only
 * knobs close the gap, no game regen required:
 *   - gpu_ws_set_gte_game_mode(1): stamp a frame as gameplay when enough GTE
 *     vertices project through RTPS/RTPT ([widescreen] gte_game_mode).
 *   - psx_ws_set_native_wide(0): the native-wide render path needs per-game
 *     recompiler hooks (cull widen / wide compositor); without them only the
 *     classic GTE X-squash + stretched present is regression-free, so force
 *     squash mode (the plain behaviour every title gets for free).
 * The activation callback then asks the runtime for the fixed display aspect,
 * honouring the aspect_ratio option the player picked in the launcher.
 */
extern void gpu_ws_set_gte_game_mode(int on);
extern void psx_ws_set_native_wide(int on);

static void crash_widescreen_activate(void) {
    gpu_ws_set_gte_game_mode(1);
    psx_ws_set_native_wide(0);
    char aspect[8];
    if (psx_mod_option_value("crash.widescreen_pack", "widescreen",
                             "aspect_ratio", aspect, sizeof aspect) &&
        strcmp(aspect, "21:9") == 0)
        (void)psx_mod_set_fixed_display_aspect(21u, 9u);
    else
        (void)psx_mod_set_fixed_display_aspect(16u, 9u);
}

PSX_MOD_CONSTRUCTOR(crash_register_widescreen_plugin) {
    (void)psx_mod_register_activation_plugin(
        "crash.widescreen", crash_widescreen_activate);
}

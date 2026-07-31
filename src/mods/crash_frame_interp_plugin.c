#include "mod_plugins.h"

#include <stdlib.h>
#include <string.h>

/*
 * Presentation-only frame interpolation for Crash Bandicoot, moved out of the
 * launcher Settings into the mod catalog (the runtime still accepts the
 * setting, but this package owns the exposed UI).
 *
 * Deliberately psx_mod_set_frame_interpolation and NOT
 * psx_mod_set_native_vblank_rate: the former blends between completed guest
 * frames and leaves VBlank, logic, timers, and audio at their stock cadence,
 * while the latter changes whole-machine realtime speed. Conflating the two is
 * how "smoother" turns into "the game runs fast", so this package exposes only
 * the presentation half.
 */
#define PKG "crash.frame_interp_pack"
#define FEATURE "frame-interpolation"

static void crash_frame_interpolation_activate(void) {
    char rate[16];
    unsigned long fps = 0ul;   /* 0 = follow measured display refresh */

    /* An unreadable or unrecognised value falls back to the manifest default
     * ("display"), which is the conservative choice: it follows the monitor
     * instead of pinning a rate the panel may not support. */
    if (psx_mod_option_value(PKG, FEATURE, "rate", rate, sizeof rate) &&
        strcmp(rate, "display") != 0) {
        char* end = rate;
        const unsigned long parsed = strtoul(rate, &end, 10);
        if (end != rate && *end == '\0') fps = parsed;
    }

    (void)psx_mod_set_frame_interpolation((uint32_t)fps);

    char blend[32];
    if (psx_mod_option_value(PKG, FEATURE, "blend", blend, sizeof blend) &&
        strcmp(blend, "linear") == 0) {
        (void)psx_mod_set_frame_interpolation_blend(
            PSX_MOD_FRAME_INTERPOLATION_LINEAR);
    } else {
        (void)psx_mod_set_frame_interpolation_blend(
            PSX_MOD_FRAME_INTERPOLATION_MOTION_ADAPTIVE);
    }
}

PSX_MOD_CONSTRUCTOR(crash_register_frame_interpolation_plugin) {
    (void)psx_mod_register_activation_plugin(
        "crash.frame-interpolation", crash_frame_interpolation_activate);
}

// Create functions at all OBJ entry points identified by the PSXRecomp OBJ matcher
//@category Functions

import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.Address;
import java.util.LinkedHashSet;

public class CreateFromOBJList extends GhidraScript {

    @Override
    public void run() throws Exception {
        // Collated unique addresses from the OBJ matcher output
        LinkedHashSet<Long> addrs = new LinkedHashSet<>();
        // LIBGTE.LIB
        addrs.add(0x80037AF4L); addrs.add(0x800375D4L);
        addrs.add(0x80038020L); addrs.add(0x80038014L);
        addrs.add(0x800370F4L); addrs.add(0x80037234L);
        addrs.add(0x80037BE4L); addrs.add(0x80036E84L);
        addrs.add(0x80036F94L); addrs.add(0x80037864L);
        addrs.add(0x80037344L); addrs.add(0x80036C04L);
        addrs.add(0x80037FB4L); addrs.add(0x80036DF4L);
        addrs.add(0x80037D04L);
        // LIBC.LIB
        addrs.add(0x80045944L);
        // LIBMCRD.LIB
        addrs.add(0x8004B2B4L); addrs.add(0x8004BA74L);
        addrs.add(0x80048DB4L); addrs.add(0x800495B4L);
        addrs.add(0x8004B3C4L);
        // 2MBYTE.OBJ
        addrs.add(0x8002E4B4L);
        // LIBSPU.LIB
        addrs.add(0x80042894L); addrs.add(0x8003F844L);
        addrs.add(0x80041904L); addrs.add(0x8003D0F4L);
        addrs.add(0x8003FD24L); addrs.add(0x80044D44L);
        addrs.add(0x80041024L); addrs.add(0x8003D3F4L);
        addrs.add(0x80041314L); addrs.add(0x80044CE4L);
        addrs.add(0x8003AA14L); addrs.add(0x800390B4L);
        addrs.add(0x80038D84L); addrs.add(0x800413A4L);
        addrs.add(0x80038D54L); addrs.add(0x80044C84L);
        addrs.add(0x80038F54L); addrs.add(0x800440F4L);
        addrs.add(0x80040324L); addrs.add(0x80038084L);
        addrs.add(0x8003A9F4L); addrs.add(0x80041594L);
        addrs.add(0x8003E8B4L); addrs.add(0x80044074L);
        addrs.add(0x8003D6D4L); addrs.add(0x80041054L);
        addrs.add(0x800413D4L); addrs.add(0x80038034L);
        addrs.add(0x80038204L); addrs.add(0x80041344L);
        addrs.add(0x80041374L); addrs.add(0x8003E2B4L);
        addrs.add(0x80038E94L); addrs.add(0x80044494L);
        addrs.add(0x80041964L);
        // LIBSN.LIB
        addrs.add(0x8001D01CL);
        // LIBAPI.LIB
        addrs.add(0x80031E84L); addrs.add(0x80045854L);
        addrs.add(0x8004B174L); addrs.add(0x800322B4L);
        addrs.add(0x8003ABB4L); addrs.add(0x80031EB4L);
        addrs.add(0x800329A4L); addrs.add(0x80036744L);
        addrs.add(0x80032984L); addrs.add(0x80038D44L);
        addrs.add(0x80038F44L); addrs.add(0x8004AEB4L);
        addrs.add(0x800329B4L); addrs.add(0x80031EC4L);
        addrs.add(0x800491B4L); addrs.add(0x80031EA4L);
        addrs.add(0x8004AEC4L); addrs.add(0x80031F14L);
        addrs.add(0x800381F4L); addrs.add(0x8003D174L);
        addrs.add(0x800329C4L); addrs.add(0x80031ED4L);
        addrs.add(0x8004AE84L); addrs.add(0x80031F24L);
        addrs.add(0x800491C4L); addrs.add(0x8004AE94L);
        addrs.add(0x800491D4L); addrs.add(0x80048E04L);
        addrs.add(0x800491A4L); addrs.add(0x80048F14L);
        addrs.add(0x80031EE4L); addrs.add(0x80031F04L);
        addrs.add(0x80049274L); addrs.add(0x80031F34L);
        addrs.add(0x800381E4L); addrs.add(0x8004AED4L);
        addrs.add(0x8003D184L); addrs.add(0x8004AEA4L);
        addrs.add(0x80037BD4L); addrs.add(0x80031E94L);
        addrs.add(0x80031EF4L);
        // LIBPAD.LIB
        addrs.add(0x8002EB94L); addrs.add(0x8002E884L);
        addrs.add(0x8002EED4L); addrs.add(0x8002EC64L);
        addrs.add(0x8002ED74L); addrs.add(0x8002E8C4L);
        addrs.add(0x8002EA34L); addrs.add(0x80030014L);
        // LIBCD.LIB
        addrs.add(0x80048A14L); addrs.add(0x80047AB4L);
        addrs.add(0x80047BF4L); addrs.add(0x800489B4L);
        addrs.add(0x80033AB8L); addrs.add(0x80048C24L);
        addrs.add(0x80048964L); addrs.add(0x80045974L);
        addrs.add(0x80045B44L); addrs.add(0x80048B34L);
        addrs.add(0x80048C74L); addrs.add(0x800489F4L);
        addrs.add(0x80047FB4L); addrs.add(0x80045AC4L);
        addrs.add(0x80046534L);
        // LIBCOMB.LIB
        addrs.add(0x8002EB80L);
        // LIBC2.LIB
        addrs.add(0x80044EC4L); addrs.add(0x800458C4L);
        addrs.add(0x80045864L); addrs.add(0x80044DA4L);
        addrs.add(0x80045954L); addrs.add(0x8004B184L);
        addrs.add(0x80044D74L); addrs.add(0x80044DE4L);
        addrs.add(0x800455F4L); addrs.add(0x80044E84L);
        addrs.add(0x80044E14L); addrs.add(0x800464B4L);
        addrs.add(0x800455A4L); addrs.add(0x80044F04L);
        addrs.add(0x8004C504L);
        // LIBDS.LIB
        addrs.add(0x80048C24L);
        // LIBETC.LIB
        addrs.add(0x800322C4L); addrs.add(0x80032D94L);
        addrs.add(0x800320A4L); addrs.add(0x800329D4L);
        addrs.add(0x80032AF4L);
        // LIBCARD.LIB
        addrs.add(0x8004B244L); addrs.add(0x8004C574L);
        addrs.add(0x800492E4L); addrs.add(0x80049314L);
        addrs.add(0x8004B294L); addrs.add(0x8004B234L);
        addrs.add(0x80049304L); addrs.add(0x800492F4L);
        addrs.add(0x80049534L); addrs.add(0x80048E14L);
        addrs.add(0x8004B2A4L); addrs.add(0x8004B254L);
        // LIBGPU.LIB
        addrs.add(0x80036B64L); addrs.add(0x80036AA4L);
        addrs.add(0x800368B4L); addrs.add(0x800369B4L);
        addrs.add(0x80036AE4L); addrs.add(0x80036A74L);
        addrs.add(0x80036AC4L); addrs.add(0x80036A14L);
        addrs.add(0x80032DC4L); addrs.add(0x80036A54L);
        addrs.add(0x80036B04L); addrs.add(0x800369F4L);
        addrs.add(0x80033844L); addrs.add(0x80036974L);
        addrs.add(0x80036844L); addrs.add(0x80036754L);
        // LIBSND.LIB
        addrs.add(0x80042EB4L); addrs.add(0x8003CC24L);
        addrs.add(0x8003B4E4L); addrs.add(0x8003B724L);
        addrs.add(0x8003BB54L); addrs.add(0x80043EE4L);
        addrs.add(0x8003C534L); addrs.add(0x8003B864L);
        addrs.add(0x8003DD34L); addrs.add(0x8003CF74L);
        addrs.add(0x8001149CL); addrs.add(0x8003F424L);
        addrs.add(0x800406F4L); addrs.add(0x8003A4C4L);
        addrs.add(0x8003A2B4L); addrs.add(0x80040B44L);
        addrs.add(0x8003A9B4L); addrs.add(0x8003BC74L);
        addrs.add(0x8003C264L); addrs.add(0x80039BC4L);
        addrs.add(0x80039AF4L); addrs.add(0x8003E154L);
        addrs.add(0x8003DEC4L); addrs.add(0x8003D3A4L);
        addrs.add(0x80043184L); addrs.add(0x80042144L);
        addrs.add(0x800443F4L); addrs.add(0x80040B54L);
        addrs.add(0x8003C0F4L); addrs.add(0x8003C3F4L);
        addrs.add(0x80043874L); addrs.add(0x8003A694L);
        addrs.add(0x80044764L); addrs.add(0x8003B3F4L);
        addrs.add(0x8003A664L); addrs.add(0x8003B254L);
        addrs.add(0x8003E164L); addrs.add(0x800428E4L);
        addrs.add(0x8003BA04L); addrs.add(0x8003CAD4L);
        addrs.add(0x8003DBB4L); addrs.add(0x800415B4L);
        addrs.add(0x8003BF94L); addrs.add(0x8003ABC4L);
        addrs.add(0x8003C624L); addrs.add(0x8003E0E4L);
        addrs.add(0x80044BC4L); addrs.add(0x8003AE54L);
        addrs.add(0x80042324L); addrs.add(0x8003BBE4L);
        addrs.add(0x80039644L); addrs.add(0x8003C6D4L);
        addrs.add(0x80040414L); addrs.add(0x800429E4L);
        addrs.add(0x8003B8D4L); addrs.add(0x8003D7B4L);
        addrs.add(0x8003B594L); addrs.add(0x800401F4L);
        addrs.add(0x80040664L); addrs.add(0x80039434L);
        addrs.add(0x8003BE24L); addrs.add(0x8003EF74L);
        addrs.add(0x80040964L); addrs.add(0x8003BA94L);
        addrs.add(0x8003C1B4L); addrs.add(0x80025E54L);
        addrs.add(0x80043944L); addrs.add(0x8003EAB4L);
        addrs.add(0x8003EC24L); addrs.add(0x8003D994L);
        addrs.add(0x80043FC4L); addrs.add(0x8003BD24L);
        addrs.add(0x8003C044L); addrs.add(0x8003A464L);
        addrs.add(0x8003D454L); addrs.add(0x8003C314L);
        addrs.add(0x8003D284L); addrs.add(0x8003DD04L);
        addrs.add(0x8003ED34L); addrs.add(0x8003EBE4L);
        addrs.add(0x8003C494L); addrs.add(0x800398E4L);
        addrs.add(0x8003A364L); addrs.add(0x80043FA4L);
        addrs.add(0x8003B324L); addrs.add(0x8003AED4L);
        addrs.add(0x8003F804L); addrs.add(0x800419A4L);
        addrs.add(0x800431F4L); addrs.add(0x80042924L);
        addrs.add(0x8003D094L); addrs.add(0x8003D194L);
        addrs.add(0x8003B944L); addrs.add(0x8003BEE4L);
        addrs.add(0x8003A864L); addrs.add(0x8003D4A4L);
        addrs.add(0x80040B34L); addrs.add(0x8003B624L);
        addrs.add(0x8003CBB4L); addrs.add(0x80040494L);
        addrs.add(0x8003C564L); addrs.add(0x80040284L);
        addrs.add(0x8003C904L); addrs.add(0x8003A914L);
        addrs.add(0x80039674L);
        // LIBGS.LIB (unique addresses already covered by LIBSND duplicates)
        // Entry point
        addrs.add(0x8002E4BCL);

        int created = 0;
        for (long addrVal : addrs) {
            Address addr = toAddr(addrVal);
            if (getFunctionContaining(addr) == null) {
                createFunction(addr, null);
                println("Created function at 0x" + Long.toHexString(addrVal));
                created++;
            }
        }
        println("Done. Created " + created + " functions.");
    }
}

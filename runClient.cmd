@echo off

echo Starting Minecraft
set APPDATA="D:\HXY\MDK\.minecraft\"
cd /D "D:\HXY\MDK\.minecraft\"
"F:\Programs\Minecraft\runtime\jre-legacy\windows-x64\jre-legacy\bin\java.exe" -XX:+UseG1GC -XX:-UseAdaptiveSizePolicy -XX:-OmitStackTraceInFastThrow -Dfml.ignoreInvalidMinecraftCertificates=True -Dfml.ignorePatchDiscrepancies=True -Dlog4j2.formatMsgNoLookups=true -Djava.net.preferIPv4Stack=true -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Xmn256m -Xmx9113m "-Djava.library.path=D:\HXY\MDK\.minecraft\versions\1.12.2\1.12.2-natives" -cp "D:\HXY\MDK\.minecraft\libraries\net\minecraftforge\forge\1.12.2-14.23.5.2855\forge-1.12.2-14.23.5.2855.jar;D:\HXY\MDK\.minecraft\libraries\org\ow2\asm\asm-debug-all\5.2\asm-debug-all-5.2.jar;D:\HXY\MDK\.minecraft\libraries\net\minecraft\launchwrapper\1.12\launchwrapper-1.12.jar;D:\HXY\MDK\.minecraft\libraries\org\jline\jline\3.5.1\jline-3.5.1.jar;D:\HXY\MDK\.minecraft\libraries\com\typesafe\akka\akka-actor_2.11\2.3.3\akka-actor_2.11-2.3.3.jar;D:\HXY\MDK\.minecraft\libraries\com\typesafe\config\1.2.1\config-1.2.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-actors-migration_2.11\1.1.0\scala-actors-migration_2.11-1.1.0.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-compiler\2.11.1\scala-compiler-2.11.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\plugins\scala-continuations-library_2.11\1.0.2_mc\scala-continuations-library_2.11-1.0.2_mc.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\plugins\scala-continuations-plugin_2.11.1\1.0.2_mc\scala-continuations-plugin_2.11.1-1.0.2_mc.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-library\2.11.1\scala-library-2.11.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-parser-combinators_2.11\1.0.1\scala-parser-combinators_2.11-1.0.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-reflect\2.11.1\scala-reflect-2.11.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-swing_2.11\1.0.1\scala-swing_2.11-1.0.1.jar;D:\HXY\MDK\.minecraft\libraries\org\scala-lang\scala-xml_2.11\1.0.2\scala-xml_2.11-1.0.2.jar;D:\HXY\MDK\.minecraft\libraries\lzma\lzma\0.0.1\lzma-0.0.1.jar;D:\HXY\MDK\.minecraft\libraries\java3d\vecmath\1.5.2\vecmath-1.5.2.jar;D:\HXY\MDK\.minecraft\libraries\net\sf\trove4j\trove4j\3.0.3\trove4j-3.0.3.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\maven\maven-artifact\3.5.3\maven-artifact-3.5.3.jar;D:\HXY\MDK\.minecraft\libraries\net\sf\jopt-simple\jopt-simple\5.0.3\jopt-simple-5.0.3.jar;D:\HXY\MDK\.minecraft\libraries\com\mojang\patchy\1.2.3\patchy-1.2.3.jar;D:\HXY\MDK\.minecraft\libraries\oshi-project\oshi-core\1.1\oshi-core-1.1.jar;D:\HXY\MDK\.minecraft\libraries\net\java\dev\jna\jna\4.4.0\jna-4.4.0.jar;D:\HXY\MDK\.minecraft\libraries\net\java\dev\jna\platform\3.4.0\platform-3.4.0.jar;D:\HXY\MDK\.minecraft\libraries\com\ibm\icu\icu4j-core-mojang\51.2\icu4j-core-mojang-51.2.jar;D:\HXY\MDK\.minecraft\libraries\com\paulscode\codecjorbis\20101023\codecjorbis-20101023.jar;D:\HXY\MDK\.minecraft\libraries\com\paulscode\codecwav\20101023\codecwav-20101023.jar;D:\HXY\MDK\.minecraft\libraries\com\paulscode\libraryjavasound\20101123\libraryjavasound-20101123.jar;D:\HXY\MDK\.minecraft\libraries\com\paulscode\librarylwjglopenal\20100824\librarylwjglopenal-20100824.jar;D:\HXY\MDK\.minecraft\libraries\com\paulscode\soundsystem\20120107\soundsystem-20120107.jar;D:\HXY\MDK\.minecraft\libraries\io\netty\netty-all\4.1.9.Final\netty-all-4.1.9.Final.jar;D:\HXY\MDK\.minecraft\libraries\com\google\guava\guava\21.0\guava-21.0.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\commons\commons-lang3\3.5\commons-lang3-3.5.jar;D:\HXY\MDK\.minecraft\libraries\commons-io\commons-io\2.5\commons-io-2.5.jar;D:\HXY\MDK\.minecraft\libraries\commons-codec\commons-codec\1.10\commons-codec-1.10.jar;D:\HXY\MDK\.minecraft\libraries\net\java\jinput\jinput\2.0.5\jinput-2.0.5.jar;D:\HXY\MDK\.minecraft\libraries\net\java\jutils\jutils\1.0.0\jutils-1.0.0.jar;D:\HXY\MDK\.minecraft\libraries\com\google\code\gson\gson\2.8.0\gson-2.8.0.jar;D:\HXY\MDK\.minecraft\libraries\com\mojang\authlib\1.5.25\authlib-1.5.25.jar;D:\HXY\MDK\.minecraft\libraries\com\mojang\realms\1.10.22\realms-1.10.22.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\commons\commons-compress\1.8.1\commons-compress-1.8.1.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\httpcomponents\httpclient\4.3.3\httpclient-4.3.3.jar;D:\HXY\MDK\.minecraft\libraries\commons-logging\commons-logging\1.1.3\commons-logging-1.1.3.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\httpcomponents\httpcore\4.3.2\httpcore-4.3.2.jar;D:\HXY\MDK\.minecraft\libraries\it\unimi\dsi\fastutil\7.1.0\fastutil-7.1.0.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\logging\log4j\log4j-api\2.8.1\log4j-api-2.8.1.jar;D:\HXY\MDK\.minecraft\libraries\org\apache\logging\log4j\log4j-core\2.8.1\log4j-core-2.8.1.jar;D:\HXY\MDK\.minecraft\libraries\org\lwjgl\lwjgl\lwjgl\2.9.4-nightly-20150209\lwjgl-2.9.4-nightly-20150209.jar;D:\HXY\MDK\.minecraft\libraries\org\lwjgl\lwjgl\lwjgl_util\2.9.4-nightly-20150209\lwjgl_util-2.9.4-nightly-20150209.jar;D:\HXY\MDK\.minecraft\libraries\com\mojang\text2speech\1.10.3\text2speech-1.10.3.jar;D:\HXY\MDK\.minecraft\libraries\optifine\OptiFine\1.12.2_HD_U_G5\OptiFine-1.12.2_HD_U_G5-installer.jar;D:\HXY\MDK\.minecraft\versions\1.12.2\1.12.2.jar" net.minecraft.launchwrapper.Launch --username sunnnysky0 --version 1.12.2 --gameDir "D:\HXY\MDK\.minecraft" --assetsDir "D:\HXY\MDK\.minecraft\assets" --assetIndex 1.12 --uuid 000000000000300A9D80306BC7AEED4B --accessToken 000000000000300A9D80306BC7AEED4B --userType Legacy --tweakClass net.minecraftforge.fml.common.launcher.FMLTweaker --versionType Forge --height 480 --width 854 --tweakClass optifine.OptiFineForgeTweaker --tweakClass optifine.OptiFineForgeTweaker
echo Minecraft terminated
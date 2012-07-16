#!bin/sh

TARGET_FILE="*.apk"

install_apk()
{
for arg in $@
do
    echo "install ${arg}"
    adb install ${arg}
done
}

install_apk $TARGET_FILE

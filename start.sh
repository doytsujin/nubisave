#!/bin/bash

echo "Start von NubiSave"
userdir=$HOME/nubisave
mountpoint=$HOME/.nubisave/nubisavemount
storages=$HOME/.nubisave/storages

mkdir -p "$mountpoint" "$storages"
scriptpath=`readlink -f $0`
scriptloc=`dirname $scriptpath`
cd $scriptloc

#groups | grep -q fuse
fusermount 2>/dev/null
if [ $? == 126 ]
then
	echo "Error: Benutzer muss zur Gruppe 'fuse' hinzugefügt werden." >&2
	exit 1
fi

echo "- Start des Splitter-Moduls"
cd splitter
./mount.sh "$mountpoint" "$storages" &
cd ..

if [ "$1" != "headless" ]
then
	# FIXME: This should be solved by some event detection
	sleep 2
	if [ ! -h "$userdir" ]
	then
		ln -sf "$mountpoint/data" "$userdir"
	fi

	echo "- Start der NubiSave-Konfigurations-GUI"
	cd bin/
	java -jar Nubisave.jar "$mountpoint"
fi

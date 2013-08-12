#!/bin/bash

echo "Start von NubiSave"
userdir=$HOME/nubisave
mountpoint=$HOME/.nubisave/nubisavemount
storages=$HOME/.storages

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

if [ ! -h "$userdir" ]
then
	ln -sf "$mountpoint/data" "$userdir"
fi

headless=0
if [ "$1" == "headless" ]
then
	headless=1
elif [ -z $DISPLAY ]
	echo "- Erzwingung des Headless-Modus!"
	headless=1
fi

if [ "$headless" == 1 ]
then
	echo "- Start der NubiSave-Konfigurations-GUI"
	cd bin/
	java -Djava.library.path=lib -jar Nubisave.jar "$mountpoint"
fi

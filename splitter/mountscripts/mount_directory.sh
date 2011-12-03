#!/bin/sh
#usage: sh mount_directory.sh mountpoint directory

if [ ! -d "$2" ]; then
    mkdir -p $2
fi
#make directory at mountpoint if it does not yet exist
mkdir -p $1
#link to directory
ln -s $2 $1/data
#create a config file to let the splitter know everything went well
mkdir -p $1/config
touch $1/config/config

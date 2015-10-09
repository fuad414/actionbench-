#!/bin/bash

export M5_PATH=../system/AndroidHelloWorld/

./build/ARM/gem5.opt --outdir=m5out-lv --stats-file=/scratch/sf117/lv_io3.txt configs/example/fs.py -b android-lv --kernel=vmlinux.smp.mouse.arm --num-cpu=1 --caches --l1d_size=32kB --l1i_size=32kB --l2cache --l2_size=512kB --cpu-type=detailed  --fetchWidth=3 --decodeWidth=3 --renameWidth=3 --dispatchWidth=3 --issueWidth=3 --wbWidth=3 --commitWidth=3 --squashWidth=3  -r 1 --dump-stats=1000

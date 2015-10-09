#!/bin/bash

export M5_PATH=../system/AndroidHelloWorld/

./build/ARM/gem5.opt --outdir=m5out-ed --stats-file=ed_io.txt configs/example/fs.py -b android-ed --kernel=vmlinux.smp.mouse.arm --num-cpu=1 --caches --l1d_size=32kB --l1i_size=32kB --l2cache --l2_size=512kB --cpu-type=detailed  --fetchWidth=3 --decodeWidth=3 --renameWidth=3 --dispatchWidth=3 --issueWidth=3 --wbWidth=3 --commitWidth=3 --squashWidth=3 --dump-stats=1000 -r 1 

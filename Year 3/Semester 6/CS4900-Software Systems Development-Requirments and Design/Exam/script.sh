#!/bin/bash

clear

svn --version

echo "checking out /src/front/input"
svn co file:///home/saurutobi/svn-repos/pro/src/front/input /home/saurutobi/workingdir
echo "file checked out"

echo "importing Validate"
svn import -m "testimport" /home/saurutobi/Desktop/Validate file:///home/saurutobi/svn-repos/pro/trunk
echo "import complete"

echo "starting copy"
 svn copy /home/saurutobi/workingdir/src/front/input file:///home/saurutobi/svn-repos/pro/src/front/back
echo "ending copy"

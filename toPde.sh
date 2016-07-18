#!/usr/bin/env bash
function regex1 { gawk 'match($0,/'$1'/, ary) {print ary['${2:-'1'}']}'; }
rm -R zip
mkdir zip
for d in */ ; do
    if [[ -d ${d}src/main/java ]]; then
        folder=${d/\//}
        echo "Converting *.java files inside "${d};
        for d3 in ${d}src/main/java/*/ ; do
               assignname=$(echo ${d3} | regex1 '^\w*\/src\/main\/java\/(\w*)')
               mkdir -p zip/${folder}/${assignname}/
            for d2 in ${d3}*.java ; do
               filename=${d2/.java/}
               filename=${filename/${d3}/}
               file_content=$( cat "${d2}" )
                if [[ " $file_content " =~ "MethodHandles.lookup" ]]
                    then
                        sed -n '/MethodHandles.lookup/,$p' ${d2} | tail -n +4 | head -n -1 | sed -e 's/^[ \t][ \t][ \t][ \t]//' > zip/${folder}/${assignname}/${filename}.pde
                    else
                        sed -n '/public class/,$p' ${d2} | sed '/private ProcessingApplet __instance = ProcessingApplet.instance;/d' | sed -e 's/__instance.//g' > zip/${folder}/${assignname}/${filename}.pde
               fi
            done
        done
        cd zip/${folder}
        zip -r ../${folder}submission *
        cd ../..
    fi
done
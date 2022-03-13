import os
import re

with os.scandir() as it:
    for file in it:
        if file.is_dir():
            if re.match('UTP\\d', file.name):
                print(file.name)
                print(file.name.split('_')[0][3:])
                os.rename(file.name, file.name.split('_')[0][3:])
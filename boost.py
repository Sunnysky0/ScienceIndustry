import win32api
import shutil
import os

os.system('.\gradlew build')

shutil.copy("F:\\Repos\\ScienceIndustry\\build\\libs\\scii-1.0-SNAPSHOT.jar", "D:\\HXY\\MDK\\.minecraft\\mods")


def start():
    res = win32api.ShellExecute(0, 'open', "D:\\HXY\\MDK\\launcher.exe", '', '', 3)
    print(res)


os.system('.\\runClient.cmd')

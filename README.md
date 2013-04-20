# Max Package Downloader

## Introduction

The Max Package Downloader is a tool I wrote following a discussion on the Cycling '74 forums about Max package management. With the new package functionality in Max 6.1, it becomes much easier to distribute packages. The idea of the Package downloader is to provide a quick and dynamic way to download pacakges from within Max.  New packages are added to a json file in this repository and the tool automatically fetches this list unload meaning it will always be up to date.

## Installation

To install the Max Package Downloader, [download](https://github.com/natcl/max_package_downloader/archive/master.zip) the repository's zip file and unzip it into your ~/Documents/Max/Packages folder. Restart Max and you should have a Package Downloader item in the extras menu.

## Usage

The tool is currently quite simple, you select the package you want and click Download, it should then download in in you packages folder.  You might then need to restart Max depending on the package installed.

## Contributing packages

Contributing packages is fairly easy.  The best way is to host your package in a Github repository and have the package structure at the root of your repository.  You then provide the link of either the master or a tagged version of the zip file to the repository file (hosted here as max_packages.json).  You can then fork this project and send me a pull request with your package information.  Since the system works with zip files, you can also host a zip of your package at the location of your convenience and provide me that link.  If for some reason your package structure is not at the root of the directory, you can specify the path with the "relative_path" item in the json file.  If for example your package folder is in root_folder/mypackage you would put "relative_path": "mypackage".

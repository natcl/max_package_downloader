# Max Package Downloader

## Introduction

The Max Package Downloader is a tool I wrote following a [discussion](http://cycling74.com/forums/topic.php?id=46239) started by Tim Bone on the Cycling '74 forums about Max package management. With the new package functionality in Max 6.1, it becomes much easier to distribute packages. The idea of the Package downloader is to provide a quick and dynamic way to download packages from within Max.  New packages are added to a json file in this repository and the tool automatically fetches this list unload meaning it will always be up to date.

## Installation

To install the Max Package Downloader, [download](https://github.com/natcl/max_package_downloader/archive/1.zip) the latest tagged version and unzip it into your `~/Documents/Max/Packages` folder. Restart Max and you should have a Package Downloader item in the extras menu.  Important: Make sure you have `Documents/Max/Packages` folder in your home directory.  You can also clone this repository, although I don't recommended it as it might be unstable at some times.  Once the Package Downloader is installed you should also be able to updated it from within itself.

## Usage

The tool is currently quite simple, you select the package you want and click Download, it should then download it in your packages folder.  You might then need to restart Max depending on the package installed.

## Contributing packages

Contributing packages is fairly easy.  The best way is to host your package in a Github repository and have the package structure at the root of your repository.  You then provide the link of either the master or a tagged version of the zip file to the repository file (hosted here as `max_packages.json`).  You can then fork this project and send me a pull request with your package information.  Since the system works with zip files, you can also host a zip of your package at the location of your convenience and provide me that link.  If for some reason your package structure is not at the root of the directory, you can specify the path with the `"relative_path"` item in the json file.  If for example your package folder is in `root_folder/mypackage` you would put `"relative_path": "mypackage"`.

Alternatively, you can create a `package-info.json` file at the root of your package directory which will enable more powerful features like automatic upgrades.  The syntax definition is in the next section.

## The package-info.json file

For the system to work in an optimal way, you can create a `package-info.json` file at the root of your package directory that will contain all the relevant information, the main `max_packages.json` file will then point to that json file.  Here is the structure:

```json
{
  "name": "The name of your package (this will also be the folder name)",
  "type": "Type of package, might extend this later to support projects and live devices, enter package between quotes for now",
  "author": "The author of the package",
  "description": "The description of the package",
  "version": "The version that will be displayed",
  "major_version": "The major version number (ie 2 if your version is 2.11)",
  "minor_version": "The minor version number (ie 11 if your version is 2.11)",
  "min_max_version": "Minimum Max version, Max 6.1.2 would be 612 between quotes",
  "min_osx_version": "Minimum osx version, for 10.6.7 you would enter 67 between quotes",
  "min_win_version": "Minimum windows version, need to check what Max reports",
  "website": "URL to a website where your package homepage lives",
  "link_mac32": "Link for the mac32 version of the zip, enter None between quotes if not relevant",
  "link_mac64": "Link for the mac64 version of the zip, enter None between quotes if not relevant",
  "link_win32": "Link for the win32 version of the zip, enter None between quotes if not relevant",
  "link_win64": "Link for the win64 version of the zip, enter None between quotes if not relevant",
  "relative_path": "Path if your package is not at the root of the zip file"
}
```
Real world example: 

```json
{
  "name": "Max Package Downloader",
  "type": "package",
  "author": "Nathanaël Lécaudé",
  "description": "The Max Package Downloader allows you to download and keep Max packages up to date.",
  "version": "1",
  "major_version": "1",
  "minor_version": "0",
  "min_max_version": "612",
  "min_osx_version": "None",
  "min_win_version": "None",
  "website": "https://github.com/natcl/max_package_downloader",
  "link_mac32": "https://github.com/natcl/max_package_downloader/archive/1.zip",
  "link_mac64": "https://github.com/natcl/max_package_downloader/archive/1.zip",
  "link_win32": "https://github.com/natcl/max_package_downloader/archive/1.zip",
  "link_win64": "https://github.com/natcl/max_package_downloader/archive/1.zip",
  "relative_path": "None"
}
```

## Thanks

Thanks a lot to Timothy Bone for the this great idea.  Thanks to Emmanuel Jourdan for the suggestions.

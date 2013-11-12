autowatch = 1;

post(max.arch);
post(max.os);

mpd_global = new Global("mpd_global");
mpd_global.max_packages = new Dict("mpd_package_info");

function populate()
{
	keys = mpd_global.max_packages.getkeys();
	keys.sort(function(a, b) {if (a.toLowerCase() < b.toLowerCase()) return -1; if (a.toLowerCase() > b.toLowerCase()) return 1; return 0;});
	outlet(0, "umenu clear");
	for (package in keys){
		if (mpd_global.max_packages.get(keys[package] + '::' + 'enabled') == "true"){
			outlet(0, "umenu append " + keys[package]);
		}
	}
	outlet(0, "umenu bang");

}

function package(package_name)
{
	messnamed('mpd_package_name', package_name);
	messnamed('mpd_link', mpd_global.max_packages.get(package_name + '::' + 'link'));
	messnamed('mpd_relative_path', mpd_global.max_packages.get(package_name + '::' + 'relative_path'));
}
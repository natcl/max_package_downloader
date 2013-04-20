autowatch = 1;

function bang()
{
	var max_packages = new Dict;

	max_packages.import_json("/Users/Nat/Source/max_package_downloader/max_packages_test.json");

	keys = max_packages.getkeys();
	post(typeof max_packages.get("Zsa.descriptors"+ "::" + "link_mac"));
	post();
}
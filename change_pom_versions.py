import os
import sys

__author__ = 'charlie'
import xml.etree.ElementTree as ET
from os.path import isdir, join
from os import listdir


def main(version):
    ET.register_namespace("", "http://maven.apache.org/POM/4.0.0")
    root_pom = "pom.xml"
    parent_pom_xml = get_pom_elements(root_pom)
    parent_version = get_version(parent_pom_xml.getroot())
    modules = get_modules(parent_pom_xml.getroot())
    directories = get_dirs()
    for directory in directories:
        path = "./%s/pom.xml" % directory
        module_pom_xml = get_pom_elements(path)
        if module_pom_xml is not None:
            changed_xml = change_parent_version(module_pom_xml, version)
            write_pom(changed_xml, path)
    changed_parent_xml = change_parent_pom_version(parent_pom_xml, version)
    write_pom(changed_parent_xml, root_pom)



def change_parent_pom_version(xml, version):
    version_element = xml.getroot().find(".//version")
    version_element.text = version
    return xml



def change_parent_version(module_xml, version):
    module_xml_element = module_xml.getroot()
    name = None
    parent_version = None
    for child in module_xml_element:
        tag = str(child.tag)
        index = tag.rfind("}")
        if index is not -1:
            tag = tag[index+1:]
        #print("Tag is '%s'" % tag)
        if tag == "name":
            name = child.text
            #print("Name is %s" % name)
            break
        if tag == "parent":
            for grandchild in child:
                grandchild_tag = str(grandchild.tag)
                g_index = grandchild_tag.rfind("}")
                if g_index is not -1:
                    grandchild_tag = grandchild_tag[g_index+1:]
                    #print("Grandchild tag is '%s'." % grandchild_tag)
                    if grandchild_tag == "version":
                        parent_version = grandchild.text
                        #print("Version is %s" % parent_version)
                        grandchild.text = version
                        break
    if name is not None and parent_version is not None:
        print("Changed parent version of %s to from %s to %s" % (name, parent_version, version))
    return module_xml


def get_pom_elements(location):
    print("Parsing xml at %s." % location)
    try:
        pom_xml = ET.parse(location)
        return pom_xml
    except IOError as ioe:
        print(ioe.message)
        return None

def get_modules(parent_pom_xml):
    modules = []
    mods = parent_pom_xml.findall(".//modules/module")
    print("Found %d modules." % len(mods))
    for mod in mods:
        modules.append(mod.text)
    print("Found modules:\n%s" % str(modules))
    return modules


def get_version(pom_xml):
    version_element = pom_xml.find(".//version")
    version = version_element.text
    print("Version is %s." % version)
    return version


def get_dirs():
    dirs = [f for f in listdir(".") if isdir(join(".", f)) and f.find(".") is -1]
    print("Found %d directories: %s" % (len(dirs), str(dirs)))
    return dirs


def write_pom(pom_xml, location):
    print("Writing XML element to %s." % location)
    pom_xml.write(location)


if __name__ == "__main__":
    main(sys.argv[1])
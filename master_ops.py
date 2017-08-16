from subprocess import call
import sys
import change_pom_version
import git_tagger

__author__ = 'charlie'


def main(version):
    print("Updating all poms to version %s" % version)
    change_pom_version.main(version)
    print("Creating and pushing out a tag for version %s" % version)
    git_tagger.main(version)
    snapshot = "%s-SNAPSHOT" % version
    print("Snapshotting all poms to %s" % snapshot)
    git_tagger.main(snapshot)
    print("Checking out %s" % snapshot)
    create_branch(snapshot)
    print("Pushing out snapshot branch %s" % snapshot)
    push(snapshot)
    print("Done.")


def push(version):
    print(call(['git', 'commit', '-m', '"Snapshotting version %s"' % version, "."]))


def create_branch(version):
    print(call(['git', 'checkout', '-b', version]))


if __name__ == "__main__":
    main(sys.argv[1])
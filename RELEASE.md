# 1.0.0 (AdamAnt) (2015-03-28)
*   Initial Release
# 1.1.0 (KingsOfTheWildFrontier) (2015-04-10)
*   Switched from JUnit to TestNG
    *   [card](https://trello.com/c/quibF3jl/2-1-1-0-switch-to-testng-in-core-pom)
    *   [card](https://trello.com/c/OQ9m31GK/3-1-1-0-make-sure-that-all-test-classes-are-test-groups-unit)
*   Added SpringEnabled
    *   [card](https://trello.com/c/U1jDDfxd/29-1-1-1-switch-all-common-utility-classes-from-abstract-to-beans)
*   Switched out all public facing utility classes from abstract to concrete. There are no more static methods in these classes.
    *   [card](https://trello.com/c/U1jDDfxd/29-1-1-1-switch-all-common-utility-classes-from-abstract-to-beans)
*   Implemented ISO-8601 Simple Date Formatter
    *   [card](https://trello.com/c/kvi9st1N/1-1-1-0-implement-default-simpledateformatter-that-returns-date-string-in-iso-8601-format)
# 1.1.1 (PussInBoots) (2015-04-17)
*   Fixed all failing tests
    *   [card](https://trello.com/c/VmbfZ7TH/22-1-1-1-fix-failing-tests)
*   Ability to deploy to Tomcat instance
    *   [card](https://trello.com/c/X0gubdWw/30-1-1-1-tomcat-7-maven-plugin)
*  Improved messaging for tests that have expected failure conditions
    *   [card](https://trello.com/c/FDK2TynQ/31-1-1-1-common-error-messages-in-validate-statements)
*   General Code Cleanup
    *   [card](https://trello.com/c/rrMkCO6n/35-1-1-1-code-cleanup)
# 1.1.2 (StandAndDeliver) (2015-04-19)
*   Removed warnings in code via addressing or suppressing
    *   [card](https://trello.com/c/Udkrgt1S/37-2-0-1-remove-warnings-either-via-fix-or-supression)
*   Updated README and POMS to include correct visioning
    *   [card](https://trello.com/c/ohKnMA0W/38-1-1-2-update-readme-s-to-include-maven-declarations-and-logging-dependancies_
*   Including logging statements in all utility classes.
    *   [card](https://trello.com/c/DK2BsxYa/36-1-1-2-include-logging-statements-in-all-utility-classes)
*   Included correct versioning in class comments
    *   [card](https://trello.com/c/AxTW35h0/40-1-1-2-correct-versioning-numbers-in-class-comments)
< 1.2.0 (PrinceCharming) (2105-04-25)
*   Documented `companyB.common.utils.FieldUtils`
    *   [card](https://trello.com/c/s6PI3ccs/41-1-2-0-document-and-test-fieldutils)
*   Provided test cases for `companyB.common.utils.FieldUtils`
    *   [card](https://trello.com/c/s6PI3ccs/41-1-2-0-document-and-test-fieldutils)
*   Increased code coverage for the following modules:
    *   Common
    *   ConfigurationEnabled
    *   ContextEnabled
    *   Decorator
    *   HttpCookieEnabled
    *   HttpSessionEnabled
# 1.2.1 (Wonderful) (2015-04-26)
*   GUID class in Common
*   Removed reference to JMock in Common
    *   [card](https://trello.com/c/5adpgRDb/39-1-2-1-switch-to-easymock-in-common)
*   Enable the specifying of testing groups from the command line
    *   [card](https://trello.com/c/1RiSTkSF/20-1-2-1-enable-passing-of-test-groups-from-command-line)
# 2.0.0 (B52) (2015-06-06)
*   Included FlywayEnabled module
    *   [card](https://trello.com/c/tiiouco9/18-2-0-include-flywayenabled)
*   Included EventLogger module
    *   [card](https://trello.com/c/XY3PugLH/17-2-0-include-eventlogger)
*   Included EncryptionEnabled
*   Added getting string version of GUID (hashed and unhashed)
*   Included EventLogger
    *   [card](https://trello.com/c/8vyBhM6F/27-eventlogger-2-0-implement-postbeanprocessor-postprocessbeforeinitialization)
*   Added Http-based utilities in `Common`.
    *   [card](https://trello.com/c/GaYCINQW/46-common-2-0-add-httpservletutils)
# 2.1.0 (GoodStuff)(2015-08-30)
*   Included CacheEnabled
    *   [card](https://trello.com/c/pCl6zPFW/43-2-2-0-create-interface-for-storing-cookie-values-keyed-on-a-unique-identifier)
*   Naming convention for releases
*   QueryMapper in Common
*   Encrypted Wrapper for Objects
    *   [card](https://trello.com/c/eDXhqL7M/49-encryptionenabled-allow-for-genericised-encrypted-object)
*   Test Groups that are to be ignored
    *   [card](https://trello.com/c/jHJKjnWV/48-core-add-test-groups-that-are-to-be-ignored)
*   Added Failsafe plugin for Integration tests
*   Fixed session reader so that lines beginning with `#` are ignored.
    *   [card](https://trello.com/c/J9K0u7Wi/45-httpsessionenabled-2-1-fix-reader-so-that-comment-lines-starting-with-are-ignored)
# 2.1.1 (LoveShack) (2015-09-04)
*   Included Python script that changes versions for all project POMs
    *   [card](https://trello.com/c/8UyBJUrr/52-2-1-2-python-script-for-auto-advancing-versions-in-pom)
*   Modified tagging Python script to include notes from this file for current release.
    *   [card](https://trello.com/c/abiiMGcD/51-2-1-1-improve-messaging-for-git-tagging)
*   Key / Value for ExternalCache implements Serializable
    *   [card](https://trello.com/c/gmuf9vjC/54-2-1-1-cacheenabled-key-value-on-externalcache-should-implement-serializable)
*   Fixed documentation for CacheEnabled to reflect correct version.
    *   [card](https://trello.com/c/ILD0SWsG/53-2-1-1-fix-cacheenabled-javadoc-to-reflect-correct-version)
# 2.1.2 (CosmicThing)
*   Fixed bug with git tagger script
*   Include Maven Enforcer plugin to enforce language level, maven version and no scala inclusion
    *   [card](https://trello.com/c/SrZwkJLv/60-core-pom-include-maven-enforcer-plugin)
*   Clearer names for POM Properties
    *   [card](https://trello.com/c/aw6IZInK/59-core-pom-clearer-names-for-properties)
*   Properties for plugin congfigurations moved to top of POM
    *   [card](https://trello.com/c/BxiUJOuX/58-core-pom-move-plugin-config-values-to-top-as-property-driven-values)
*   Including Maven Enforcer Plugin
    *   [card](https://trello.com/c/SrZwkJLv/60-core-pom-include-maven-enforcer-plugin)
*   Including Maven Versions Plugin
    *   [card](https://trello.com/c/3YHkJhx5/62-core-pom-replace-python-versions-script-with-maven-versions-plugin)
# 2.2.0 (Funplex)
*   Removed all Http-specific modules
    *   [card](https://trello.com/c/DxWFOjgT/64-core-pom-2-2-0-depricate-httpcookieenabled-and-httpsessionenabled-modules)
# 2.3.0 (Cocteau Twins)
*   Removed SimpleRegexUtils (Apache does a much better job)
*   Introduced JdbcUtils
*   Logging changes - following SLF4J formatting standards
*   More Java8 Goodness
*   Updated JaCoCo to fail on < 80% coverage
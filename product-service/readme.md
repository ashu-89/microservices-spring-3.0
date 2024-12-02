Docker command to:
    start mongo db image
    attach a volume to it d:/data
    attach it to hosts port 27017
    pass username and password

docker run --name mongodb -d -p 27017:27017 -v d:/data:/data/db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongodb/mongodb-community-server

** - **
HOW TO SET UP JAVA AND PROJECT (INTELLIJ IDEA, POM) CONSISTENTLY


What You've Accomplished:
Manual Installation of OpenJDK 21:

Downloaded from a trusted source (Microsoft's website). ✅
Ensured you have the latest, stable JDK version for your project.
Set JAVA_HOME Variable:

Pointed it to OpenJDK 21.
Made sure environment variables align system-wide.
Verified Maven Uses OpenJDK 21:

Checked mvn -v and confirmed it recognizes the correct JDK version. Maven relies heavily on this setup, so verifying this is crucial.
IntelliJ IDEA Configuration:

Updated Project SDK and language level to JDK 21:
Ensures compatibility with features like records and other Java 21 enhancements.
Verified the terminal within IntelliJ IDEA also used OpenJDK 21 (java -version and mvn -v).
Consistent pom.xml Configuration:

Updated the Maven Compiler Plugin to specify source and target as 21.
Ensured the project uses compatible versions of libraries (like Lombok) and plugins.
Why This Matters?
Unified Environment: No discrepancies between IDE, terminal, or CI/CD.
Java 21 Features: Ensures full access to modern language features (e.g., record, pattern matching, etc.).
Future-Proofing: Aligning your environment with a long-term supported (LTS) version of Java.
What’s Next?
CI/CD Integration: If you're deploying to staging or production, ensure those environments also run OpenJDK 21.
Leverage Java 21 Features: Explore new features like pattern matching, virtual threads (Project Loom), and improved garbage collection.
Optimize Builds: Consider testing your builds with mvn clean install on different environments to ensure smooth portability.
Amazing work getting everything set up! Let me know if you'd like to explore any of Java 21's features or need help with CI/CD configurations. 🚀

** - **
# IntelliJ plugin for XIR language

## Build

```bash
./gradlew buildPlugin
```

Built plugin zip appears in:

```text
build/distributions/
```

## Install in IntelliJ IDEA

1. Build plugin ZIP with `./gradlew buildPlugin`.
2. Open IntelliJ IDEA.
3. Go to `Settings/Preferences` -> `Plugins`.
4. Click gear icon -> `Install Plugin from Disk...`.
5. Select ZIP file from `build/distributions/`.
6. Restart IntelliJ IDEA when prompted.

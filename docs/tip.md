```sh
mvn versions:set -DnewVersion=0.0.2
git commit -m "Bump version to 0.0.2"
git tag -a v0.0.2 -m "Release v0.0.2"
git push origin main --tags
```

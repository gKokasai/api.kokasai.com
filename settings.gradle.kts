rootProject.name = "kokasai-api"

include(
    ":flowerkt:core",
    ":flowerkt:database",
    ":flowerkt:database-exposed",
    ":flowerkt:database-exposed-sqlite",
    ":flowerkt:file",
    ":flowerkt:file-webdav",
    ":flowerkt:mail",
    ":flowerkt:mail-sendgrid",
    ":flowerkt:session-exposed"
)

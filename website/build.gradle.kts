plugins {
    `git-publish`
}

gitPublish {
    repoUri = RELEASE_WEBSITE
    branch = "gh-pages"
    contents.from(
        "src",
        *artifacts
            .map { "../$it/build/docs" }
            .toTypedArray()
    )
}

tasks["gitPublishCopy"].dependsOn(
    *artifacts
        .map { it.replace('/', ':') }
        .map { ":$it:dokka" }
        .toTypedArray()
)

val artifacts: List<String>
    get() = listOf(
        "defaults",
        "defaults-compiler",
        "defaults-features/defaults-jvm",
        "defaults-features/defaults-android"
    )
load(":skydoc_test.bzl", "skydoc_test")

package(
    default_testonly = 1,
    default_visibility = ["//src:__subpackages__"],
)

filegroup(
    name = "srcs",
    testonly = 0,
    srcs = glob(["**"]),
    visibility = ["//src:__pkg__"],
)

java_test(
    name = "SkydocTest",
    size = "medium",
    srcs = ["SkydocTest.java"],
    shard_count = 1,
    visibility = ["//devtools/blaze/main:__pkg__"],
    deps = [
        "//src/main/java/com/google/devtools/build/lib:syntax",
        "//src/main/java/com/google/devtools/build/lib/vfs",
        "//src/main/java/com/google/devtools/build/skydoc:skydoc_lib",
        "//src/main/java/com/google/devtools/build/skydoc/rendering",
        "//src/test/java/com/google/devtools/build/lib:testutil",
        "//src/test/java/com/google/devtools/build/lib/skylark:testutil",
        "//third_party:guava",
        "//third_party:junit4",
        "//third_party:truth",
    ],
)

skydoc_test(
    name = "simple_test",
    golden_file = "testdata/simple_test/golden.txt",
    input_file = "testdata/simple_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "unknown_name",
    golden_file = "testdata/unknown_name_test/golden.txt",
    input_file = "testdata/unknown_name_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "multiple_rules_test",
    golden_file = "testdata/multiple_rules_test/golden.txt",
    input_file = "testdata/multiple_rules_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "android_basic_test",
    golden_file = "testdata/android_basic_test/golden.txt",
    input_file = "testdata/android_basic_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "apple_basic_test",
    golden_file = "testdata/apple_basic_test/golden.txt",
    input_file = "testdata/apple_basic_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "cpp_basic_test",
    golden_file = "testdata/cpp_basic_test/golden.txt",
    input_file = "testdata/cpp_basic_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "java_basic_test",
    golden_file = "testdata/java_basic_test/golden.txt",
    input_file = "testdata/java_basic_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

skydoc_test(
    name = "multiple_files_test",
    golden_file = "testdata/multiple_files_test/golden.txt",
    input_file = "testdata/multiple_files_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
    deps = [
        "testdata/multiple_files_test/dep.bzl",
        "testdata/multiple_files_test/inner_dep.bzl",
    ],
)

skydoc_test(
    name = "misc_apis_test",
    golden_file = "testdata/misc_apis_test/golden.txt",
    input_file = "testdata/misc_apis_test/input.bzl",
    skydoc = "//src/main/java/com/google/devtools/build/skydoc",
)

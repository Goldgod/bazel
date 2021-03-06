// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.bazel;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.devtools.build.lib.events.Event;
import com.google.devtools.build.lib.runtime.BlazeModule;
import com.google.devtools.build.lib.runtime.BlazeServerStartupOptions;
import com.google.devtools.build.lib.runtime.CommandEnvironment;
import com.google.devtools.build.lib.util.AbruptExitException;
import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionDocumentationCategory;
import com.google.devtools.common.options.OptionEffectTag;
import com.google.devtools.common.options.OptionsBase;

/** Provides Bazel startup flags. */
public class BazelStartupOptionsModule extends BlazeModule {
  /** Bazelrc file flags. */
  public static final class Options extends OptionsBase {
    @Option(
        name = "bazelrc",
        defaultValue = "null", // NOTE: purely decorative, rc files are read by the client.
        documentationCategory = OptionDocumentationCategory.BAZEL_CLIENT_OPTIONS,
        effectTags = {OptionEffectTag.CHANGES_INPUTS},
        valueHelp = "<path>",
        help =
            "The location of the user .bazelrc file containing default values of "
                + "Bazel options. If unspecified, Bazel uses the first .bazelrc file it finds in "
                + "the following two locations: the workspace directory, then the user's home "
                + "directory. Use /dev/null to disable the search for a user rc file, e.g. in "
                + "release builds.")
    public String blazerc;

    @Option(
        name = "master_bazelrc",
        defaultValue = "true", // NOTE: purely decorative, rc files are read by the client.
        documentationCategory = OptionDocumentationCategory.BAZEL_CLIENT_OPTIONS,
        effectTags = {OptionEffectTag.CHANGES_INPUTS},
        help =
            "If this option is false, the master bazelrcs are not read. Otherwise, Bazel looks for "
                + "master rcs in three locations, reading them all, in order: "
                + "$workspace/tools/bazel.rc, a .bazelrc file near the bazel binary, and the "
                + "global rc, /etc/bazel.bazelrc.")
    public boolean masterBlazerc;
  }

  @Override
  public Iterable<Class<? extends OptionsBase>> getStartupOptions() {
    return ImmutableList.of(Options.class);
  }

  /**
   * Post a deprecation warning about batch mode. This is in beforeCommand, and not earlier, so that
   * we can post the warning event to the correctly set up channels.
   */
  @Override
  public void beforeCommand(CommandEnvironment env) throws AbruptExitException {
    BlazeServerStartupOptions startupOptions =
        Preconditions.checkNotNull(
            env.getRuntime()
                .getStartupOptionsProvider()
                .getOptions(BlazeServerStartupOptions.class));
    if (startupOptions.batch) {
      env.getReporter()
          .handle(
              Event.warn(
                  "--batch mode is deprecated. Please instead explicitly shut down your Bazel "
                      + "server using the command \"bazel shutdown\"."));
    }
  }
}

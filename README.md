# JetBrains timeline navigation test captures

These images come from an off-screen Swing integration fixture on the fixed build for Kilo-Org/kilocode#14917. They show the state before and after clicking the timeline. They are not manual IDE screenshots. The pre-fix regression failed with scroll position 3045 instead of 908.

| Before click | After click |
|---|---|
| ![Before click](screenshots/before-click.png) | ![After click](screenshots/after-click.png) |

The patch has 111 passing targeted tests and passing typecheck. The complete package test command failed with three frontend failures; two also reproduce on the unmodified baseline, and the full-suite ConnectionDelay failure remains unexplained. No manual IDE run or independent human review is claimed. These captures do not establish that the full suite passed.

## Baseline follow-up (2026-10-08)

Code PR: [Kilo-Org/kilocode#14926](https://github.com/Kilo-Org/kilocode/pull/14926). This branch contains verification artifacts only.

On unchanged base `42efc95370e61d6668bd2cb05f955228e5d4155a`, the complete frontend suite ran 4,491 tests: 4,489 passed, 2 failed, 0 skipped; command exit 1, 834.75 seconds. Both failures were the existing TitleButton layout cases. The ConnectionDelay class passed its 15 tests in that run.

A separate diagnostic runner then replayed the original `test hide event sees updated connection state on EDT` 200 times, with its original setup, assertions and teardown. 196 passed and 4 failed with the same `NoSuchElementException` at `ConnectionDelayTest.kt:435` (exit 1, 484.32 seconds including compilation). The baseline worktree remained clean. This establishes that the intermittent failure predates the timeline patch; it does not fix the underlying race or turn the failing package run into a pass.

- [Full baseline results](verification/baseline-frontend-summary.json) and [actual command](verification/baseline-command.json)
- [Replay results](verification/replay-summary.json), [actual command](verification/replay-command.json), and [log](verification/replay.log)
- [Replay driver](verification/ConnectionDelayRepeatTest.java), [Gradle init script](verification/replay.init.gradle), and [runtime versions](verification/runtime-versions.json)

The init script records the absolute source path used in this environment. To reproduce elsewhere, place the Java driver in a separate directory and set the script’s `srcDir` to that directory, then run the recorded Gradle command with that script and local JDK/proxy paths. The script adds a diagnostic entry point; it does not edit any repository test, production source, or assertion. An initial init-script configuration failure ran no tests and is retained separately in the local evidence.

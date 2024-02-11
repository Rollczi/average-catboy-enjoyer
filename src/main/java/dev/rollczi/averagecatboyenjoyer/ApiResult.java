package dev.rollczi.averagecatboyenjoyer;

import java.util.List;

record ApiResult(
    List<ImagesResult> images_results
) { }

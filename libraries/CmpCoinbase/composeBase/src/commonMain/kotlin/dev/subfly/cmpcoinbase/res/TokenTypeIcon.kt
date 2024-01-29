package dev.subfly.cmpcoinbase.res

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

private var _tokenTypeIcon: ImageVector? = null

val tokenTypeIcon: ImageVector
    get() {
        if (_tokenTypeIcon != null) {
            return _tokenTypeIcon!!
        }
        _tokenTypeIcon = ImageVector.Builder(
            name = "Xml to svg converter",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(21f, 7f)
                lineToRelative(-9f, -5f)
                lineTo(3f, 7f)
                verticalLineToRelative(10f)
                lineToRelative(9f, 5f)
                lineToRelative(9f, -5f)
                lineTo(21f, 7f)
                close()
                moveTo(12f, 4.29f)
                lineToRelative(5.91f, 3.28f)
                lineTo(14.9f, 9.24f)
                curveTo(14.17f, 8.48f, 13.14f, 8f, 12f, 8f)
                reflectiveCurveTo(9.83f, 8.48f, 9.1f, 9.24f)
                lineTo(6.09f, 7.57f)
                lineTo(12f, 4.29f)
                close()
                moveTo(11f, 19.16f)
                lineToRelative(-6f, -3.33f)
                verticalLineTo(9.26f)
                lineToRelative(3.13f, 1.74f)
                curveTo(8.04f, 11.31f, 8f, 11.65f, 8f, 12f)
                curveToRelative(0f, 1.86f, 1.27f, 3.43f, 3f, 3.87f)
                verticalLineTo(19.16f)
                close()
                moveTo(10f, 12f)
                curveToRelative(0f, -1.1f, 0.9f, -2f, 2f, -2f)
                reflectiveCurveToRelative(2f, 0.9f, 2f, 2f)
                reflectiveCurveToRelative(-0.9f, 2f, -2f, 2f)
                reflectiveCurveTo(10f, 13.1f, 10f, 12f)
                close()
                moveTo(13f, 19.16f)
                verticalLineToRelative(-3.28f)
                curveToRelative(1.73f, -0.44f, 3f, -2.01f, 3f, -3.87f)
                curveToRelative(0f, -0.35f, -0.04f, -0.69f, -0.13f, -1.01f)
                lineTo(19f, 9.26f)
                lineToRelative(0f, 6.57f)
                lineTo(13f, 19.16f)
                close()
            }
        }.build()
        return _tokenTypeIcon!!
    }

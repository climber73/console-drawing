package com.creditsuisse.drawing

/**
 *  Converts shape into list of points
 *  The interface is used by AbstractCanvas when it asked to draw a shape
 */
interface ShapeConverter {
    fun convert(shape: Shape): List<Point>
}
package com.creditsuisse.drawing

interface ShapeConverter {
    fun convert(shape: Shape): List<Point>
}
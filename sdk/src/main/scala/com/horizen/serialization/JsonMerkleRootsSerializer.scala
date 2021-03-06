package com.horizen.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind
import com.fasterxml.jackson.databind.SerializerProvider
import com.horizen.utils.{ByteArrayWrapper, BytesUtils}

import scala.collection.mutable.Map
import scala.collection.mutable.Iterable

class JsonMerkleRootsSerializer extends databind.JsonSerializer[Option[Map[ByteArrayWrapper, Array[Byte]]]] {

  override def serialize(t: Option[Map[ByteArrayWrapper, Array[Byte]]], jsonGenerator: JsonGenerator, serializerProvider: SerializerProvider): Unit = {
    if(t.isDefined){
      var listOfPair : Iterable[Pair] = t.get.map(k => Pair(BytesUtils.toHexString(k._1.data), BytesUtils.toHexString(k._2)))
      jsonGenerator.writeObject(listOfPair)
    }else{
      jsonGenerator.writeStartArray()
      jsonGenerator.writeEndArray()
    }
  }
}

private case class Pair(key : String, value : String)
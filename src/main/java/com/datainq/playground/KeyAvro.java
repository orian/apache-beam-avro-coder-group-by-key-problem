/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.datainq.playground;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class KeyAvro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 3416081414867223089L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"KeyAvro\",\"namespace\":\"com.datainq.playground\",\"fields\":[{\"name\":\"prop0\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<KeyAvro> ENCODER =
      new BinaryMessageEncoder<KeyAvro>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<KeyAvro> DECODER =
      new BinaryMessageDecoder<KeyAvro>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<KeyAvro> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<KeyAvro> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<KeyAvro>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this KeyAvro to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a KeyAvro from a ByteBuffer. */
  public static KeyAvro fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String prop0;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public KeyAvro() {}

  /**
   * All-args constructor.
   * @param prop0 The new value for prop0
   */
  public KeyAvro(java.lang.String prop0) {
    this.prop0 = prop0;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return prop0;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: prop0 = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'prop0' field.
   * @return The value of the 'prop0' field.
   */
  public java.lang.String getProp0() {
    return prop0;
  }

  /**
   * Sets the value of the 'prop0' field.
   * @param value the value to set.
   */
  public void setProp0(java.lang.String value) {
    this.prop0 = value;
  }

  /**
   * Creates a new KeyAvro RecordBuilder.
   * @return A new KeyAvro RecordBuilder
   */
  public static com.datainq.playground.KeyAvro.Builder newBuilder() {
    return new com.datainq.playground.KeyAvro.Builder();
  }

  /**
   * Creates a new KeyAvro RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new KeyAvro RecordBuilder
   */
  public static com.datainq.playground.KeyAvro.Builder newBuilder(com.datainq.playground.KeyAvro.Builder other) {
    return new com.datainq.playground.KeyAvro.Builder(other);
  }

  /**
   * Creates a new KeyAvro RecordBuilder by copying an existing KeyAvro instance.
   * @param other The existing instance to copy.
   * @return A new KeyAvro RecordBuilder
   */
  public static com.datainq.playground.KeyAvro.Builder newBuilder(com.datainq.playground.KeyAvro other) {
    return new com.datainq.playground.KeyAvro.Builder(other);
  }

  /**
   * RecordBuilder for KeyAvro instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<KeyAvro>
    implements org.apache.avro.data.RecordBuilder<KeyAvro> {

    private java.lang.String prop0;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.datainq.playground.KeyAvro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.prop0)) {
        this.prop0 = data().deepCopy(fields()[0].schema(), other.prop0);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing KeyAvro instance
     * @param other The existing instance to copy.
     */
    private Builder(com.datainq.playground.KeyAvro other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.prop0)) {
        this.prop0 = data().deepCopy(fields()[0].schema(), other.prop0);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'prop0' field.
      * @return The value.
      */
    public java.lang.String getProp0() {
      return prop0;
    }

    /**
      * Sets the value of the 'prop0' field.
      * @param value The value of 'prop0'.
      * @return This builder.
      */
    public com.datainq.playground.KeyAvro.Builder setProp0(java.lang.String value) {
      validate(fields()[0], value);
      this.prop0 = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'prop0' field has been set.
      * @return True if the 'prop0' field has been set, false otherwise.
      */
    public boolean hasProp0() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'prop0' field.
      * @return This builder.
      */
    public com.datainq.playground.KeyAvro.Builder clearProp0() {
      prop0 = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KeyAvro build() {
      try {
        KeyAvro record = new KeyAvro();
        record.prop0 = fieldSetFlags()[0] ? this.prop0 : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<KeyAvro>
    WRITER$ = (org.apache.avro.io.DatumWriter<KeyAvro>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<KeyAvro>
    READER$ = (org.apache.avro.io.DatumReader<KeyAvro>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
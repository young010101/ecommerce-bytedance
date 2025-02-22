// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: idl/response.proto

package com.sky.protos;

public final class ResponseOuterClass {
  private ResponseOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Response)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * 定义一个string类型的字段，字段名字为data, 序号为1
     * </pre>
     *
     * <code>string data = 1;</code>
     * @return The data.
     */
    java.lang.String getData();
    /**
     * <pre>
     * 定义一个string类型的字段，字段名字为data, 序号为1
     * </pre>
     *
     * <code>string data = 1;</code>
     * @return The bytes for data.
     */
    com.google.protobuf.ByteString
        getDataBytes();

    /**
     * <pre>
     * 定义一个int32类型的字段，字段名字为status, 序号为2
     * </pre>
     *
     * <code>int32 status = 2;</code>
     * @return The status.
     */
    int getStatus();
  }
  /**
   * <pre>
   * 定义数据结构，message 你可以想象成java的class，c语言中的struct
   * </pre>
   *
   * Protobuf type {@code Response}
   */
  public static final class Response extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Response)
      ResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Response.newBuilder() to construct.
    private Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Response() {
      data_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Response();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sky.protos.ResponseOuterClass.internal_static_Response_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sky.protos.ResponseOuterClass.internal_static_Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sky.protos.ResponseOuterClass.Response.class, com.sky.protos.ResponseOuterClass.Response.Builder.class);
    }

    public static final int DATA_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object data_ = "";
    /**
     * <pre>
     * 定义一个string类型的字段，字段名字为data, 序号为1
     * </pre>
     *
     * <code>string data = 1;</code>
     * @return The data.
     */
    @java.lang.Override
    public java.lang.String getData() {
      java.lang.Object ref = data_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        data_ = s;
        return s;
      }
    }
    /**
     * <pre>
     * 定义一个string类型的字段，字段名字为data, 序号为1
     * </pre>
     *
     * <code>string data = 1;</code>
     * @return The bytes for data.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getDataBytes() {
      java.lang.Object ref = data_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        data_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int STATUS_FIELD_NUMBER = 2;
    private int status_ = 0;
    /**
     * <pre>
     * 定义一个int32类型的字段，字段名字为status, 序号为2
     * </pre>
     *
     * <code>int32 status = 2;</code>
     * @return The status.
     */
    @java.lang.Override
    public int getStatus() {
      return status_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(data_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, data_);
      }
      if (status_ != 0) {
        output.writeInt32(2, status_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(data_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, data_);
      }
      if (status_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, status_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.sky.protos.ResponseOuterClass.Response)) {
        return super.equals(obj);
      }
      com.sky.protos.ResponseOuterClass.Response other = (com.sky.protos.ResponseOuterClass.Response) obj;

      if (!getData()
          .equals(other.getData())) return false;
      if (getStatus()
          != other.getStatus()) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
      hash = (37 * hash) + STATUS_FIELD_NUMBER;
      hash = (53 * hash) + getStatus();
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sky.protos.ResponseOuterClass.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.sky.protos.ResponseOuterClass.Response prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * 定义数据结构，message 你可以想象成java的class，c语言中的struct
     * </pre>
     *
     * Protobuf type {@code Response}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Response)
        com.sky.protos.ResponseOuterClass.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.sky.protos.ResponseOuterClass.internal_static_Response_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.sky.protos.ResponseOuterClass.internal_static_Response_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.sky.protos.ResponseOuterClass.Response.class, com.sky.protos.ResponseOuterClass.Response.Builder.class);
      }

      // Construct using com.sky.protos.ResponseOuterClass.Response.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        data_ = "";
        status_ = 0;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.sky.protos.ResponseOuterClass.internal_static_Response_descriptor;
      }

      @java.lang.Override
      public com.sky.protos.ResponseOuterClass.Response getDefaultInstanceForType() {
        return com.sky.protos.ResponseOuterClass.Response.getDefaultInstance();
      }

      @java.lang.Override
      public com.sky.protos.ResponseOuterClass.Response build() {
        com.sky.protos.ResponseOuterClass.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.sky.protos.ResponseOuterClass.Response buildPartial() {
        com.sky.protos.ResponseOuterClass.Response result = new com.sky.protos.ResponseOuterClass.Response(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(com.sky.protos.ResponseOuterClass.Response result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.data_ = data_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.status_ = status_;
        }
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.sky.protos.ResponseOuterClass.Response) {
          return mergeFrom((com.sky.protos.ResponseOuterClass.Response)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.sky.protos.ResponseOuterClass.Response other) {
        if (other == com.sky.protos.ResponseOuterClass.Response.getDefaultInstance()) return this;
        if (!other.getData().isEmpty()) {
          data_ = other.data_;
          bitField0_ |= 0x00000001;
          onChanged();
        }
        if (other.getStatus() != 0) {
          setStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                data_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000001;
                break;
              } // case 10
              case 16: {
                status_ = input.readInt32();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.lang.Object data_ = "";
      /**
       * <pre>
       * 定义一个string类型的字段，字段名字为data, 序号为1
       * </pre>
       *
       * <code>string data = 1;</code>
       * @return The data.
       */
      public java.lang.String getData() {
        java.lang.Object ref = data_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          data_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * 定义一个string类型的字段，字段名字为data, 序号为1
       * </pre>
       *
       * <code>string data = 1;</code>
       * @return The bytes for data.
       */
      public com.google.protobuf.ByteString
          getDataBytes() {
        java.lang.Object ref = data_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          data_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * 定义一个string类型的字段，字段名字为data, 序号为1
       * </pre>
       *
       * <code>string data = 1;</code>
       * @param value The data to set.
       * @return This builder for chaining.
       */
      public Builder setData(
          java.lang.String value) {
        if (value == null) { throw new NullPointerException(); }
        data_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 定义一个string类型的字段，字段名字为data, 序号为1
       * </pre>
       *
       * <code>string data = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearData() {
        data_ = getDefaultInstance().getData();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 定义一个string类型的字段，字段名字为data, 序号为1
       * </pre>
       *
       * <code>string data = 1;</code>
       * @param value The bytes for data to set.
       * @return This builder for chaining.
       */
      public Builder setDataBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) { throw new NullPointerException(); }
        checkByteStringIsUtf8(value);
        data_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }

      private int status_ ;
      /**
       * <pre>
       * 定义一个int32类型的字段，字段名字为status, 序号为2
       * </pre>
       *
       * <code>int32 status = 2;</code>
       * @return The status.
       */
      @java.lang.Override
      public int getStatus() {
        return status_;
      }
      /**
       * <pre>
       * 定义一个int32类型的字段，字段名字为status, 序号为2
       * </pre>
       *
       * <code>int32 status = 2;</code>
       * @param value The status to set.
       * @return This builder for chaining.
       */
      public Builder setStatus(int value) {

        status_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * 定义一个int32类型的字段，字段名字为status, 序号为2
       * </pre>
       *
       * <code>int32 status = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000002);
        status_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Response)
    }

    // @@protoc_insertion_point(class_scope:Response)
    private static final com.sky.protos.ResponseOuterClass.Response DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.sky.protos.ResponseOuterClass.Response();
    }

    public static com.sky.protos.ResponseOuterClass.Response getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Response>
        PARSER = new com.google.protobuf.AbstractParser<Response>() {
      @java.lang.Override
      public Response parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Response> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Response> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.sky.protos.ResponseOuterClass.Response getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Response_descriptor;
  private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022idl/response.proto\"(\n\010Response\022\014\n\004data" +
      "\030\001 \001(\t\022\016\n\006status\030\002 \001(\005B\020\n\016com.sky.protos" +
      "b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Response_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Response_descriptor,
        new java.lang.String[] { "Data", "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

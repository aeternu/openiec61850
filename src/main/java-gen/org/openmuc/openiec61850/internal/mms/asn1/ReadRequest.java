/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;
import org.openmuc.jasn1.ber.ReverseByteArrayOutputStream;
import org.openmuc.jasn1.ber.types.BerBoolean;

public class ReadRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

    public byte[] code = null;
    private BerBoolean specificationWithResult = null;
    private VariableAccessSpecification variableAccessSpecification = null;

    public ReadRequest() {
    }

    public ReadRequest(byte[] code) {
        this.code = code;
    }

    public void setSpecificationWithResult(BerBoolean specificationWithResult) {
        this.specificationWithResult = specificationWithResult;
    }

    public BerBoolean getSpecificationWithResult() {
        return specificationWithResult;
    }

    public void setVariableAccessSpecification(VariableAccessSpecification variableAccessSpecification) {
        this.variableAccessSpecification = variableAccessSpecification;
    }

    public VariableAccessSpecification getVariableAccessSpecification() {
        return variableAccessSpecification;
    }

    public int encode(OutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(OutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        int sublength;

        sublength = variableAccessSpecification.encode(os);
        codeLength += sublength;
        codeLength += BerLength.encodeLength(os, sublength);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
        os.write(0xA1);
        codeLength += 1;

        if (specificationWithResult != null) {
            codeLength += specificationWithResult.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 0
            os.write(0x80);
            codeLength += 1;
        }

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;

    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        subCodeLength += berTag.decode(is);
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
            specificationWithResult = new BerBoolean();
            subCodeLength += specificationWithResult.decode(is, false);
            subCodeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            subCodeLength += length.decode(is);
            variableAccessSpecification = new VariableAccessSpecification();
            subCodeLength += variableAccessSpecification.decode(is, null);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
        }
        throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: "
                + subCodeLength);

    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        boolean firstSelectedElement = true;
        if (specificationWithResult != null) {
            sb.append("\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("specificationWithResult: ").append(specificationWithResult);
            firstSelectedElement = false;
        }

        if (!firstSelectedElement) {
            sb.append(",\n");
        }
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (variableAccessSpecification != null) {
            sb.append("variableAccessSpecification: ");
            variableAccessSpecification.appendAsString(sb, indentLevel + 1);
        }
        else {
            sb.append("variableAccessSpecification: <empty-required-field>");
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}

package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {
    private final OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    private static byte[] compress(byte[] bytes) {
        ArrayList<Byte> holder = new ArrayList<>(); //placeholder for our return array

        //insert meta data
        for (int i = 0; i < 12; i++)
            holder.add(bytes[i]);

        //compress maze
        byte[] temp = new byte[8];
        for (int i = 12; i < bytes.length; i += 8) {
            for (int j = 0; j < 8; j++)
                temp[j] = i + j < bytes.length ? bytes[i + j] : 0;
            holder.add(toOneUnsignedByte(temp));
        }

        //prepare the result array
        byte[] gridAsArray = new byte[holder.size()];
        for (int i = 0; i < gridAsArray.length; i++) {
            gridAsArray[i] = holder.get(i);
        }
        return gridAsArray;
    }

    private static byte toOneUnsignedByte(byte[] array) throws IllegalArgumentException {
        if (array.length > 8)
            throw new IllegalArgumentException("The given array length is bigger then 8");
        int sum = 0;
        for (int i = 0; i < array.length; i++)
            sum += array[i] * Math.pow(2, i);
        return (byte) sum;
    }

    @Override
    public void write(int b) throws IOException {
        //TODO: what we need to implement here????
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.out.write(compress(b));
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
        super.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
        super.close();
    }
}

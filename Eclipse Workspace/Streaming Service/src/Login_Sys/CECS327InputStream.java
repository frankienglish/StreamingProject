/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login_Sys;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

public class CECS327InputStream extends InputStream
{
    protected int total;
    protected int mark;
    protected int pos;
    protected byte[] buf;
    protected byte[] nextBuf;
    protected int fragment;
    protected int FRAGMENT_SIZE;
    protected String fileName;
    
    public CECS327InputStream(final String fileName) throws IOException {
        this.total = 0;
        this.mark = 0;
        this.pos = 0;
        this.fragment = 0;
        this.FRAGMENT_SIZE = 8192;
        this.fileName = fileName;
        final File file = new File(fileName);
        this.total = (int)file.length();
        this.buf = new byte[this.FRAGMENT_SIZE];
        this.nextBuf = new byte[this.FRAGMENT_SIZE];
        this.getBuff(this.fragment);
        ++this.fragment;
    }
    
    protected void getBuff(final int fragment) throws IOException {
        final File file = new File(this.fileName);
        final FileInputStream inputStream = new FileInputStream(file);
        inputStream.skip(fragment * this.FRAGMENT_SIZE);
        inputStream.read(this.nextBuf);
        inputStream.close();
    }
    
    @Override
    public synchronized int read() throws IOException {
        if (this.pos >= this.total) {
            this.pos = 0;
            return -1;
        }
        final int posmod = this.pos % this.FRAGMENT_SIZE;
        if (posmod == 0) {
            for (int i = 0; i < this.FRAGMENT_SIZE; ++i) {
                this.buf[i] = this.nextBuf[i];
            }
            this.getBuff(this.fragment);
            ++this.fragment;
        }
        final int p = this.pos % this.FRAGMENT_SIZE;
        ++this.pos;
        return this.buf[p] & 0xFF;
    }
    
    @Override
    public synchronized int read(final byte[] b, final int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (this.pos >= this.total) {
            return -1;
        }
        final int avail = this.total - this.pos;
        if (len > avail) {
            len = avail;
        }
        if (len <= 0) {
            return 0;
        }
        for (int i = off; i < off + len; ++i) {
            b[i] = (byte)this.read();
        }
        return len;
    }
    
    @Override
    public synchronized long skip(final long n) throws IOException {
        long k = this.total - this.pos;
        if (n < k) {
            k = ((n < 0L) ? 0L : n);
        }
        this.pos += (int)k;
        this.getBuff(this.fragment = (int)Math.floor(this.pos / this.FRAGMENT_SIZE));
        this.getBuff(++this.fragment);
        return k;
    }
    
    @Override
    public synchronized int available() {
        return this.total - this.pos;
    }
    
    @Override
    public boolean markSupported() {
        return true;
    }
    
    @Override
    public void mark(final int readAheadLimit) {
        this.mark = this.pos;
    }
    
    @Override
    public synchronized void reset() throws IOException {
        this.pos = this.mark;
        this.getBuff(this.fragment = (int)Math.floor(this.pos / this.FRAGMENT_SIZE));
        this.getBuff(++this.fragment);
    }
    
    @Override
    public void close() throws IOException {
    }
}
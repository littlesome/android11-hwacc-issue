package com.gf.issue;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity {
    private GLSurfaceView glv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glv = findViewById(R.id.glv_main);
        GLSurfaceViewRender render = new GLSurfaceViewRender();
        glv.setRenderer(render);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    static class BufferUtil {
        public static FloatBuffer mBuffer;

        public static FloatBuffer floatToBuffer(float[] a) {
            ByteBuffer mbb = ByteBuffer.allocateDirect(a.length * 4);
            mbb.order(ByteOrder.nativeOrder());
            mBuffer = mbb.asFloatBuffer();
            mBuffer.put(a);
            mBuffer.position(0);
            return mBuffer;
        }
    }

    class GLSurfaceViewRender implements GLSurfaceView.Renderer {

        private float[] mTriangleArray = {
                -0.5f, -0.25f, 0, 0.5f, -0.25f, 0, 0.0f, 0.559016994f, 0};

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glClearColor(0.0f, 0.5f, 0f, 0.5f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

            gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

            FloatBuffer mTriangleBuffer = BufferUtil
                    .floatToBuffer(mTriangleArray);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mTriangleBuffer);
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

            gl.glLoadIdentity();
            gl.glTranslatef(-0.2f, 0.0f, 0f);

        }
    }
}

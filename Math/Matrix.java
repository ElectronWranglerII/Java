/**
* Matrix Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Matrix {
	/**
	 * Creates a projection matrix
	 * @param ScreenWidth
	 * @param ScreenHeight
	 * @param Near
	 * @param Far
	 * @param FOV
	 * @return
	 */
	public static Matrix4 projectionPerspective(int ScreenWidth, int ScreenHeight, float Near, float Far, float FOV) {
		float AspectRatio = (float)ScreenWidth / (float)ScreenHeight;
		float YScale = (float) (1.0f / Math.tan(Math.toRadians(FOV / 2.0f)));
		float XScale = YScale / AspectRatio;
		float FrustumLength = Far - Near;
		//
		Vector4 M0 = new Vector4(XScale, 0.0f, 0.0f, 0.0f);
		Vector4 M1 = new Vector4(0.0f, YScale, 0.0f, 0.0f);
		Vector4 M2 = new Vector4(0.0f, 0.0f, -((Far + Near) / FrustumLength), -1.0f);
		Vector4 M3 = new Vector4(0.0f, 0.0f, -((2.0f * Near * Far) / FrustumLength), 0.0f);

		return new Matrix4(M0, M1, M2, M3);
	}
	
	/**
	 * Creates a transformation matrix
	 * @param Translation
	 * @param RX
	 * @param RY
	 * @param Scale
	 * @return
	 */
	public static Matrix4 transformation(Vector3 Translation, float RX, float RY, Vector3 Scale) {
		Matrix4 Matrix = new Matrix4();
		Matrix.translate(Translation);
		Matrix.rotate(Matrix4.rotation(new Vector3(1.0f, 0.0f, 0.0f), (float) Math.toRadians(RX)));
		Matrix.rotate(Matrix4.rotation(new Vector3(0.0f, 1.0f, 0.0f), (float) Math.toRadians(RY)));
		Matrix.scale(Scale);
		return Matrix;
	}
	
	/**
	 * Creates a transformation matrix
	 * @param Translation
	 * @param RX
	 * @param RY
	 * @param RZ
	 * @param Scale
	 * @return
	 */
	public static Matrix4 transformation(Vector3 Translation, float RX, float RY, float RZ, Vector3 Scale) {
		Matrix4 Matrix = new Matrix4();
		Matrix.translate(Translation);
		Matrix.rotate(Matrix4.rotation(new Vector3(1.0f, 0.0f, 0.0f), (float) Math.toRadians(RX)));
		Matrix.rotate(Matrix4.rotation(new Vector3(0.0f, 1.0f, 0.0f), (float) Math.toRadians(RY)));
		Matrix.rotate(Matrix4.rotation(new Vector3(0.0f, 0.0f, 1.0f), (float) Math.toRadians(RZ)));
		Matrix.scale(Scale);
		return Matrix;
	}
	
	/**
	 * Creates a view matrix
	 * @param Position
	 * @param Rotation
	 * @return
	 */
	public static Matrix4 view(Vector3 Position, Vector3 Rotation) {
		Matrix4 Matrix = new Matrix4();
		Matrix.rotate(Matrix4.rotation(new Vector3(1.0f, 0.0f, 0.0f), (float) Math.toRadians(Rotation.x())));
		Matrix.rotate(Matrix4.rotation(new Vector3(0.0f, 1.0f, 0.0f), (float) Math.toRadians(Rotation.y())));
		Matrix.rotate(Matrix4.rotation(new Vector3(0.0f, 0.0f, 1.0f), (float) Math.toRadians(Rotation.z())));
		Vector3 Translation = new Vector3(-Position.x(), -Position.y(), -Position.z());
		Matrix.translate(Translation);
		return Matrix;
	}
}

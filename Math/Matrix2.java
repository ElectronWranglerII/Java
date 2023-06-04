/**
* Helios (TM) 3D Engine (Java): 2 x 2 Matrix Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

public class Matrix2 {
	private float M00, M10;
	private float M01, M11;
	
	public Matrix2(float M00, float M01, float M10, float M11) {
		this.M00 = M00;
		this.M01 = M01;
		this.M10 = M10;
		this.M11 = M11;
	}
	
	public Matrix2(Vector2 M0, Vector2 M1) {
		this.M00 = M0.x();
		this.M01 = M0.y();
		
		this.M10 = M1.x();
		this.M11 = M1.y();
	}
	
	//Static methods

	public static float determinant(Matrix2 Matrix) {
		return Matrix.M00 * Matrix.M11 - Matrix.M01 * Matrix.M10;
	}
	
	public static Matrix2 identity() {
		return new Matrix2(1.0f, 0.0f, 0.0f, 1.0f);
	}
	
	public static Matrix2 multiply(Matrix2 A, Matrix2 B) {
		float M00 = A.M00 * B.M00 + A.M10 * B.M01;
		float M01 = A.M01 * B.M00 + A.M11 * B.M01;
		
		float M10 = A.M00 * B.M10 + A.M10 * B.M11;
		float M11 = A.M01 * B.M10 + A.M11 * B.M11;

		return new Matrix2(M00, M01, M10, M11);
	}
	
	public static Matrix2 negate(Matrix2 Matrix) {
		return new Matrix2(-Matrix.M00, -Matrix.M01, -Matrix.M10, -Matrix.M11);
	}
	
	public void copy(Matrix2 Matrix) {
		Matrix.set(this);
	}
	
	public float determinant() {
		return Matrix2.determinant(this);
	}
	
	public void set(Matrix2 Matrix) {
		this.M00 = Matrix.M00;
		this.M01 = Matrix.M01;
		this.M10 = Matrix.M10;
		this.M11 = Matrix.M11;
	}
	
	public void zeroize() {
		this.M00 = 0.0f;
		this.M01 = 0.0f;
		
		this.M10 = 0.0f;
		this.M11 = 0.0f;
	}
}

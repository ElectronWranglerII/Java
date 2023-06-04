/**
* Helios (TM) 3D Engine (Java): 2 x 2 Matrix Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

public class Matrix3 {
	private float M00, M10, M20;
	private float M01, M11, M21;
	private float M02, M12, M22;
	
	public Matrix3() {
		this.identity();
	}
	
	public Matrix3(Vector3 M0, Vector3 M1, Vector3 M2) {
		this.M00 = M0.x();
		this.M01 = M0.y();
		this.M02 = M0.z();
		
		this.M10 = M1.x();
		this.M11 = M1.y();
		this.M12 = M1.z();
		
		this.M20 = M2.x();
		this.M21 = M2.y();
		this.M22 = M2.z();
	}
	
	//Static methods
	/*
	 * Calculates the determinant
	 */
	public static float determinant(float M00, float M10, float M20,
									float M01, float M11, float M21,
									float M02, float M12, float M22) {
		float D =	M00 * (M11 * M22 - M12 * M21) +
					M01 * (M12 * M20 - M10 * M22) +
					M02 * (M10 * M21 - M11 * M20);
		return D;
	}
	
	/*
	 * Multiplies the matrix
	 */
	public static Matrix3 multiply(Matrix3 A, Matrix3 B) {
		float M00 = A.M00 * B.M00 + A.M10 * B.M01 + A.M20 * B.M02;
		float M01 = A.M01 * B.M00 + A.M11 * B.M01 + A.M21 * B.M02;
		float M02 = A.M02 * B.M00 + A.M12 * B.M01 + A.M22 * B.M02;
		Vector3 M0 = new Vector3(M00, M01, M02);
		
		float M10 = A.M00 * B.M10 + A.M10 * B.M11 + A.M20 * B.M12;
		float M11 = A.M01 * B.M10 + A.M11 * B.M11 + A.M21 * B.M12;
		float M12 = A.M02 * B.M10 + A.M12 * B.M11 + A.M22 * B.M12;
		Vector3 M1 = new Vector3(M10, M11, M12);
		
		float M20 = A.M00 * B.M20 + A.M10 * B.M21 + A.M20 * B.M22;
		float M21 = A.M01 * B.M20 + A.M11 * B.M21 + A.M21 * B.M22;
		float M22 = A.M02 * B.M20 + A.M12 * B.M21 + A.M22 * B.M22;
		Vector3 M2 = new Vector3(M20, M21, M22);

		return new Matrix3(M0, M1, M2);
	}
	
	public float determinant() {
		float D =	this.M00 * (this.M11 * this.M22 - this.M12 * this.M21) +
					this.M01 * (this.M12 * this.M20 - this.M10 * this.M22) +
					this.M02 * (this.M10 * this.M21 - this.M11 * this.M20);
		return D;
	}
	
	public void identity() {
		this.M00 = 1.0f;
		this.M01 = 0.0f;
		this.M02 = 0.0f;
		
		this.M10 = 0.0f;
		this.M11 = 1.0f;
		this.M12 = 0.0f;
		
		this.M20 = 0.0f;
		this.M21 = 0.0f;
		this.M22 = 1.0f;
	}
	
	public void zeroize() {
		this.M00 = 0.0f;
		this.M01 = 0.0f;
		this.M02 = 0.0f;
		
		this.M10 = 0.0f;
		this.M11 = 0.0f;
		this.M12 = 0.0f;
		
		this.M20 = 0.0f;
		this.M21 = 0.0f;
		this.M22 = 0.0f;
	}
}

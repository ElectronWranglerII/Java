/**
* 3D Vector Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

public class Vector3 {
	private float X;
	private float Y;
	private float Z;
	
	public Vector3() {
		this.X = 0.0f;
		this.Y = 0.0f;
		this.Z = 0.0f;
	}
	
	public Vector3(float X, float Y, float Z) {
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public Vector3(Vector3 Vector) {
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	public Vector3(Vector2 Vector, float Z) {
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Z;
	}
	
	public Vector3(Vector3 A, Vector3 B) {
		this.X = A.x() - B.x();
		this.Y = A.y() - B.y();
		this.Z = A.z() - B.z();
	}
	
	/*
	 * STATIC METHODS
	 * All computation is performed using these methods, and they all return
	 * a the result in a new Vector3 or float object.
	 */
	
	/**
	 * Adds the XY values to the vector, returning the result in a new vector
	 * @param Vector the vector
	 * @param X a float
	 * @param Y a float
	 * @param Z a float
	 * @return a new vector
	 */
	public static Vector3 add(Vector3 Vector, float X, float Y, float Z) {
		float NX, NY, NZ;
		NX = Vector.x() + X;
		NY = Vector.y() + Y;
		NZ = Vector.z() + Z;
		return new Vector3(NX, NY, NZ);
	}
	
	/**
	 * Adds the "right side" vector to the "left side" vector, returning the 
	 * result in a new vector
	 * @param A left-side vector
	 * @param B right-side vector
	 * @return a new vector
	 */
	public static Vector3 add(Vector3 A, Vector3 B) {
		float X, Y, Z;
		X = A.x() + B.x();
		Y = A.y() + B.y();
		Z = A.z() + B.z();
		return new Vector3(X, Y, Z);
	}
	
	/**
	 * Calculates the axis-angle between two vectors, returning the result as a
	 * new vector4
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new vector representing the axis-angle
	 */
	public static Vector4 axisAngle(Vector3 A, Vector3 B) {
		float Angle;
		Vector3 Axis, NormA, NormB;
		NormA = Vector3.normalize(A);
		NormB = Vector3.normalize(B);
		Angle = (float) Math.acos(Vector3.dot(NormA, NormB));
		Axis = Vector3.cross(NormA, NormB);
		return new Vector4(Axis, Angle);
	}
	
	/**
	 * Calculates the cross product between two vectors, returning the result
	 * as a new vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new vector representing the cross product
	 */
	public static Vector3 cross(Vector3 A, Vector3 B) {
		float X, Y, Z;
		X = A.y() * B.z() - A.z() * B.y();
		Y = A.z() * B.x() - A.x() * B.z();
		Z = A.x() * B.y() - A.y() * B.x();
		return new Vector3(X, Y, Z);
	}
	
	/**
	 * Computes the dot product between two vectors, returning the result as a float
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new float representing the dot product
	 */
	public static float dot(Vector3 A, Vector3 B) {
		return A.x() * B.x() + A.y() * B.y() + A.z() * B.z();
	}
	
	public static float length(Vector3 Vector) {
		return (float) Math.sqrt(Vector.x() * Vector.x() + Vector.y() * Vector.y() + Vector.z() * Vector.z());
	}
	
	/**
	 * Negates the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector3 negate(Vector3 Vector) {
		return new Vector3(-Vector.x(), -Vector.y(), -Vector.z());
	}
	
	/**
	 * Normalizes the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector3 normalize(Vector3 Vector) {
		float X, Y, Z;
		float Length = (float) Math.sqrt(Vector.x() * Vector.x() + Vector.y() * Vector.y() + Vector.z() * Vector.z());
		if(Length != 0.0f) {
			Length = 1.0f / Length;
			X = Vector.x() * Length;
			Y = Vector.y() * Length;
			Z = Vector.z() * Length;
		} else {
			X = 0.0f;
			Y = 0.0f;
			Z = 0.0f;
		}
		return new Vector3(X, Y, Z);
	}
	
	/**
	 * Scales the vector by another vector, returning the result in a new vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector representing the XY scale
	 * @return a new vector
	 */
	public static Vector3 scale(Vector3 A, Vector3 B) {
		float NX, NY, NZ;
		NX = A.x() * B.x();
		NY = A.y() * B.y();
		NZ = A.z() * B.z();
		return new Vector3(NX, NY, NZ);
	}
	
	/**
	 * Subtracts the "right side" vector from the :left side" vector, returning
	 * the result in a vew vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new vector
	 */
	public static Vector3 subtract(Vector3 A, Vector3 B) {
		float X, Y, Z;
		X = A.x() - B.x();
		Y = A.y() - B.y();
		Z = A.z() - B.z();
		return new Vector3(X, Y, Z);
	}
	
	/*
	 * INSTANCE METHODS
	 * All computations are forwarded from these methods to the static methods
	 */
	
	//Accessors
	
	/**
	 * Calcualtes the angle between this vector and another vector, returning
	 * the result as a float
	 * @param Vector a vector
	 * @return a float representing the angle
	 */
	public Vector4 axisAngle(Vector3 Vector) {
		return Vector3.axisAngle(this, Vector);
	}
	
	public void copy(Vector3 Vector) {
		Vector.set(this.X, this.Y, this.Z);
	}
	
	/**
	 * Calculates the cross product between this vector and another vector,
	 * returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector representing the cross product
	 */
	public Vector3 cross(Vector3 Vector) {
		return new Vector3(Vector3.cross(this, Vector));
	}
	
	/**
	 * Computes the dot product between this vector and another vector, returning
	 * the result as a float
	 * @param Vector a vector
	 * @return a float representing the dot product
	 */
	public float dot(Vector3 Vector) {
		return Vector3.dot(this, Vector);
	}
	
	public boolean equals(Object Vector) {
		if(this == Vector) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Calculates the vector's length, returning the result as a float
	 * @return a float representing the length
	 */
	public float length() {
		return Vector3.length(this);
	}
	
	/**
	 * Returns a string containing the vector's contents
	 * Format: "(X, Y, Z)"
	 */
	public String toString() {
		return "(" + this.X + ", " + this.Y + ", " + this.Z + ")";
	}
	
	public float x() {
		return this.X;
	}
	
	public float y() {
		return this.Y;
	}
	
	public float z() {
		return this.Z;
	}
	
	//Mutators
	
	/**
	 * Adds another vector to this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void add(Vector3 Vector) {
		this.set(Vector3.add(this, Vector));
	}
	
	/**
	 * Sets the vector's components to zero	
	 */
	public void clear() {
		this.X = 0.0f;
		this.Y = 0.0f;
		this.Z = 0.0f;
	}
	
	/**
	 * Negates this vector, overwriting the existing values
	 */
	public void negate() {
		this.set(Vector3.negate(this));
	}
	
	/**
	 * Normalizes this vector, overwriting the existing values
	 */
	public void normalize() {
		this.set(Vector3.normalize(this));
	}
	
	/**
	 * Scales this vector by another vector, overwriting the existing values
	 * @param Vector a vector representing the XY scale
	 */
	public void scale(Vector3 Vector) {
		this.set(Vector3.scale(this, Vector));
	}
	
	/**
	 * Sets new values for the vector
	 * @param X a float representing the X component
	 * @param Y a float representing the Y component
	 * @param Z a float representing the Z component
	 */
	public void set(float X, float Y, float Z) {
		this.X = X;
		this.Y = Y;
		this.Z = X;
	}
	
	/**
	 * Sets new values for the vector
	 * @param Vector
	 */
	public void set(Vector3 Vector) {
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	/**
	 * Subtracts a vector from this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void subtract(Vector3 Vector) {
		Vector3.subtract(this, Vector);
	}
	
	public void x(float X) {
		this.X = X;
	}
	
	public void y(float Y) {
		this.Y = Y;
	}
	
	public void z(float Z) {
		this.Z = Z;
	}
}

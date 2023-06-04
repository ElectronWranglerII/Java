/**
* 2D Vector Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

public class Vector2 {
	private float X;
	private float Y;
	
	public Vector2() {
		this.X = 0.0f;
		this.Y = 0.0f;
	}
	
	public Vector2(float X, float Y) {
		this.X = X;
		this.Y = Y;
	}
	
	public Vector2(Vector2 Vector) {
		this.X = Vector.x();
		this.Y = Vector.y();
	}
	
	/*
	 * STATIC METHODS
	 * All computation is performed using these methods, and they all return
	 * a the result in a new vector2 or float object.
	 */
	
	/**
	 * Adds the XY values to the vector, returning the result in a new vector
	 * @param Vector the vector
	 * @param X a float
	 * @param Y a float
	 * @return a new vector
	 */
	public static Vector2 add(Vector2 Vector, float X, float Y) {
		float NX, NY;
		NX = Vector.x() + X;
		NY = Vector.y() + Y;
		return new Vector2(NX, NY);
	}
	
	/**
	 * Adds the "right side" vector to the "left side" vector, returning the 
	 * result in a new vector
	 * @param A left-side vector
	 * @param B right-side vector
	 * @return a new vector
	 */
	public static Vector2 add(Vector2 A, Vector2 B) {
		float X, Y;
		X = A.x() + B.x();
		Y = A.y() + B.y();
		return new Vector2(X, Y);
	}
	
	/**
	 * Calculates the angle between two vectors, returning the result as a new float
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new float
	 */
	public static float angle(Vector2 A, Vector2 B) {
		float Angle, Length;
		Length = A.length() * B.length();
		if(Length != 0.0f) {
			Angle = Vector2.dot(A, B) / Length;
			if(Angle < -1.0f) {
				Angle = -1.0f;
			}else if(Angle > 1.0f) {
				Angle = 1.0f;
			}
			Angle = (float) Math.acos(Angle);
		} else {
			Angle = 0.0f;
		}
		return Angle;
	}
	
	/**
	 * Computes the dot product between a vector and two scalar values, returning
	 * the result as a float
	 * @param Vector
	 * @param X a float
	 * @param Y a float
	 * @return a float representing the dot product
	 */
	public static float dot(Vector2 Vector, float X, float Y) {
		return Vector.x() * X + Vector.y() * Y;
	}
	
	/**
	 * Computes the dot product between two vectors, returning the result as a float
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new float representing the dot product
	 */
	public static float dot(Vector2 A, Vector2 B) {
		return A.x() * B.x() + A.y() * B.y();
	}
	
	public static float length(Vector2 Vector) {
		return (float) Math.sqrt(Vector.x() * Vector.x() + Vector.y() * Vector.y());
	}
	
	/**
	 * Negates the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector2 negate(Vector2 Vector) {
		return new Vector2(-Vector.x(), -Vector.y());
	}
	
	/**
	 * Normalizes the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector2 normalize(Vector2 Vector) {
		float X, Y;
		float Length = (float) Math.sqrt(Vector.x() * Vector.x() + Vector.y() * Vector.y());
		if(Length != 0.0f) {
			Length = 1.0f / Length;
			X = Vector.x() * Length;
			Y = Vector.y() * Length;
		} else {
			X = 0.0f;
			Y = 0.0f;
		}
		return new Vector2(X, Y);
	}
	
	/**
	 * Scales the vector by XY, returning the result in a new vector
	 * @param Vector the vector
	 * @param X a float representing the X scale
	 * @param Y a float representing the Y scale
	 * @return a new vector
	 */
	public static Vector2 scale(Vector2 Vector, float X, float Y) {
		float NX, NY;
		NX = Vector.x() * X;
		NY = Vector.y() * Y;
		return new Vector2(NX, NY);
	}
	
	/**
	 * Scales the vector by another vector, returning the result in a new vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector representing the XY scale
	 * @return a new vector
	 */
	public static Vector2 scale(Vector2 A, Vector2 B) {
		float NX, NY;
		NX = A.x() * B.x();
		NY = A.y() * B.y();
		return new Vector2(NX, NY);
	}
	
	/**
	 * subtracts the XY values from the vector, returning the result in a new vector
	 * @param Vector the vector
	 * @param X a float
	 * @param Y a float
	 * @return a new vector
	 */
	public static Vector2 subtract(Vector2 Vector, float X, float Y) {
		float NX, NY;
		NX = Vector.x() - X;
		NY = Vector.y() - Y;
		return new Vector2(NX, NY);
	}
	
	/**
	 * Subtracts the "right side" vector from the :left side" vector, returning
	 * the result in a vew vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new vector
	 */
	public static Vector2 subtract(Vector2 A, Vector2 B) {
		float X, Y;
		X = A.x() - B.x();
		Y = A.y() - B.y();
		return new Vector2(X, Y);
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
	public float angle(Vector2 Vector) {
		return Vector2.angle(this, Vector);
	}
	
	public void copy(Vector2 Vector) {
		Vector.set(this.X, this.Y);
	}
	
	/**
	 * Computes the dot product between this vector and another vector, returning
	 * the result as a float
	 * @param Vector a vector
	 * @return a float representing the dot product
	 */
	public float dot(Vector2 Vector) {
		return Vector2.dot(this, Vector);
	}
	
	/**
	 * Calculates the vector's length, returning the result as a float
	 * @return a float representing the length
	 */
	public float length() {
		return Vector2.length(this);
	}
	
	/**
	 * Returns a string containing the vector's contents
	 * Format: "(X, Y)"
	 */
	public String toString() {
		return "(" + this.X + ", " + this.Y + ")";
	}
	
	public float x() {
		return this.X;
	}
	
	public float y() {
		return this.Y;
	}
	
	//Mutators
	
	/**
	 * Adds the XY values to this vector, overwriting the existing values
	 * @param X a float
	 * @param Y a float
	 */
	public void add(float X, float Y) {
		this.set(Vector2.add(this, X, Y));
	}
	
	/**
	 * Adds another vector to this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void add(Vector2 Vector) {
		this.set(Vector2.add(this, Vector));
	}
	
	/**
	 * Sets the vector's components to zero	
	 */
	public void clear() {
		this.X = 0.0f;
		this.Y = 0.0f;
	}
	
	/**
	 * Negates this vector, overwriting the existing values
	 */
	public void negate() {
		this.set(Vector2.negate(this));
	}
	
	/**
	 * Normalizes this vector, overwriting the existing values
	 */
	public void normalize() {
		this.set(Vector2.normalize(this));
	}
	
	/**
	 * Scales this vector by XY, overwriting the existing values
	 * @param X a float representing the X scale
	 * @param Y a float representing the Y scale
	 */
	public void scale(float X, float Y) {
		this.set(Vector2.scale(this, X, Y));
	}
	
	/**
	 * Scales this vector by another vector, overwriting the existing values
	 * @param Vector a vector representing the XY scale
	 */
	public void scale(Vector2 Vector) {
		this.set(Vector2.scale(this, Vector));
	}
	
	/**
	 * Sets new values for the vector
	 * @param Vector
	 */
	public void set(float X, float Y) {
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * Sets new values for the vector
	 * @param Vector
	 */
	public void set(Vector2 Vector) {
		this.X = Vector.x();
		this.Y = Vector.y();
	}
	
	/**
	 * subtracts the XY values from this vector, overwriting the existing values
	 * @param X a float
	 * @param Y a float
	 */
	public void subtract(float X, float Y) {
		this.set(Vector2.subtract(this, X, Y));
	}
	
	/**
	 * Subtracts a vector from this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void subtract(Vector2 Vector) {
		Vector2.subtract(this, Vector);
	}
	
	public void x(float X) {
		this.X = X;
	}
	
	public void y(float Y) {
		this.Y = Y;
	}
}

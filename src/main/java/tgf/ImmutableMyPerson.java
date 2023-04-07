package tgf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Immutable implementation of {@link MyPerson}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableMyPerson.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableMyPerson.of()}.
 */
@SuppressWarnings("all")
public final class ImmutableMyPerson extends MyPerson {
  private final String name;
  private final String company;
  private final Color color;
  private final Date date;

  private ImmutableMyPerson(String name, String company, Color color, Date date) {
    this.name = Objects.requireNonNull(name, "name");
    this.company = Objects.requireNonNull(company, "company");
    this.color = Objects.requireNonNull(color, "color");
    this.date = Objects.requireNonNull(date, "date");
  }

  private ImmutableMyPerson(
      ImmutableMyPerson original,
      String name,
      String company,
      Color color,
      Date date) {
    this.name = name;
    this.company = company;
    this.color = color;
    this.date = date;
  }

  /**
   * @return The value of the {@code name} attribute
   */
  @Override
  String getName() {
    return name;
  }

  /**
   * @return The value of the {@code company} attribute
   */
  @Override
  String getCompany() {
    return company;
  }

  /**
   * @return The value of the {@code color} attribute
   */
  @Override
  Color getColor() {
    return color;
  }

  /**
   * @return The value of the {@code date} attribute
   */
  @Override
  Date getDate() {
    return date;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link MyPerson#getName() name} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param name A new value for name
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableMyPerson withName(String name) {
    if (this.name.equals(name)) return this;
    String newValue = Objects.requireNonNull(name, "name");
    return new ImmutableMyPerson(this, newValue, this.company, this.color, this.date);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link MyPerson#getCompany() company} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param company A new value for company
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableMyPerson withCompany(String company) {
    if (this.company.equals(company)) return this;
    String newValue = Objects.requireNonNull(company, "company");
    return new ImmutableMyPerson(this, this.name, newValue, this.color, this.date);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link MyPerson#getColor() color} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param color A new value for color
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableMyPerson withColor(Color color) {
    if (this.color == color) return this;
    Color newValue = Objects.requireNonNull(color, "color");
    return new ImmutableMyPerson(this, this.name, this.company, newValue, this.date);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link MyPerson#getDate() date} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param date A new value for date
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableMyPerson withDate(Date date) {
    if (this.date == date) return this;
    Date newValue = Objects.requireNonNull(date, "date");
    return new ImmutableMyPerson(this, this.name, this.company, this.color, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableMyPerson} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableMyPerson
        && equalTo((ImmutableMyPerson) another);
  }

  private boolean equalTo(ImmutableMyPerson another) {
    return name.equals(another.name)
        && company.equals(another.company)
        && color.equals(another.color)
        && date.equals(another.date);
  }

  /**
   * Computes a hash code from attributes: {@code name}, {@code company}, {@code color}, {@code date}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + name.hashCode();
    h = h * 17 + company.hashCode();
    h = h * 17 + color.hashCode();
    h = h * 17 + date.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code MyPerson} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "MyPerson{"
        + "name=" + name
        + ", company=" + company
        + ", color=" + color
        + ", date=" + date
        + "}";
  }

  /**
   * Construct a new immutable {@code MyPerson} instance.
   * @param name The value for the {@code name} attribute
   * @param company The value for the {@code company} attribute
   * @param color The value for the {@code color} attribute
   * @param date The value for the {@code date} attribute
   * @return An immutable MyPerson instance
   */
  public static ImmutableMyPerson of(String name, String company, Color color, Date date) {
    return new ImmutableMyPerson(name, company, color, date);
  }

  /**
   * Creates an immutable copy of a {@link MyPerson} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable MyPerson instance
   */
  public static ImmutableMyPerson copyOf(MyPerson instance) {
    if (instance instanceof ImmutableMyPerson) {
      return (ImmutableMyPerson) instance;
    }
    return ImmutableMyPerson.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableMyPerson ImmutableMyPerson}.
   * @return A new ImmutableMyPerson builder
   */
  public static ImmutableMyPerson.Builder builder() {
    return new ImmutableMyPerson.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableMyPerson ImmutableMyPerson}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  public static final class Builder {
    private static final long INIT_BIT_NAME = 0x1L;
    private static final long INIT_BIT_COMPANY = 0x2L;
    private static final long INIT_BIT_COLOR = 0x4L;
    private static final long INIT_BIT_DATE = 0x8L;
    private long initBits = 0xfL;

    private String name;
    private String company;
    private Color color;
    private Date date;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code MyPerson} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(MyPerson instance) {
      Objects.requireNonNull(instance, "instance");
      name(instance.getName());
      company(instance.getCompany());
      color(instance.getColor());
      date(instance.getDate());
      return this;
    }

    /**
     * Initializes the value for the {@link MyPerson#getName() name} attribute.
     * @param name The value for name 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder name(String name) {
      this.name = Objects.requireNonNull(name, "name");
      initBits &= ~INIT_BIT_NAME;
      return this;
    }

    /**
     * Initializes the value for the {@link MyPerson#getCompany() company} attribute.
     * @param company The value for company 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder company(String company) {
      this.company = Objects.requireNonNull(company, "company");
      initBits &= ~INIT_BIT_COMPANY;
      return this;
    }

    /**
     * Initializes the value for the {@link MyPerson#getColor() color} attribute.
     * @param color The value for color 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder color(Color color) {
      this.color = Objects.requireNonNull(color, "color");
      initBits &= ~INIT_BIT_COLOR;
      return this;
    }

    /**
     * Initializes the value for the {@link MyPerson#getDate() date} attribute.
     * @param date The value for date 
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder date(Date date) {
      this.date = Objects.requireNonNull(date, "date");
      initBits &= ~INIT_BIT_DATE;
      return this;
    }

    /**
     * Builds a new {@link ImmutableMyPerson ImmutableMyPerson}.
     * @return An immutable instance of MyPerson
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableMyPerson build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableMyPerson(null, name, company, color, date);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<String>();
      if ((initBits & INIT_BIT_NAME) != 0) attributes.add("name");
      if ((initBits & INIT_BIT_COMPANY) != 0) attributes.add("company");
      if ((initBits & INIT_BIT_COLOR) != 0) attributes.add("color");
      if ((initBits & INIT_BIT_DATE) != 0) attributes.add("date");
      return "Cannot build MyPerson, some of required attributes are not set " + attributes;
    }
  }
}

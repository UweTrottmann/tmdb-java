# Keep the entity class names if they are used.
-keepnames class com.uwetrottmann.tmdb2.entities.**
# Keep the entity members as they are if the class is kept.
# Note: this prevents R8 from removing fields only used in constructors.
-keepclassmembers class com.uwetrottmann.tmdb2.entities.** {
    <fields>;
    <init>(...);
}
# Keep all enum classes as is, above rules make no reasonable difference.
-keep class com.uwetrottmann.tmdb2.enumerations.** { *; }

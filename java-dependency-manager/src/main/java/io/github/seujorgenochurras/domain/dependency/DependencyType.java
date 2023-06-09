package io.github.seujorgenochurras.domain.dependency;

public enum DependencyType {
   TEST_IMPLEMENTATION("testImplementation"),
   TEST_RUNTIME_ONLY("testRuntimeOnly"),
   TEST_COMPILE_ONLY("testCompileOnly"),

   RUNTIME_ONLY("runtimeOnly"),
   IMPLEMENTATION("implementation"),

   API("api"),

   COMPILE_ONLY("compileOnly"),
   COMPILE_ONLY_API("compileOnlyApi");

   public final String typeName;

   DependencyType(String typeName) {
      this.typeName = typeName;
   }
   public static DependencyType getTypeByName(String typeName){
      for(DependencyType type : values()){
         if(type.typeName.equals(typeName.trim())) return type;
      }
      throw new DependencyTypeNotFoundException("Dependency type \"" + typeName + "\" not found");
   }
}
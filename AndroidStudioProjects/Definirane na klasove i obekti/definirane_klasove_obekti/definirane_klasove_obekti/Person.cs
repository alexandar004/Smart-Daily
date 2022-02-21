using System;
using System.Collections.Generic;
using System.Text;

namespace definirane_klasove_obekti
{
    class Person
    {
        private string name;
        private int age;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        public int Age
        {
            get { return age; }
            set { age = value; }
        }

        public Person(string name, int age)
        {
            Name = name;
            Age = age;
        }

        public string IntroduceYourself()
        {
            return $"Hello my name is {this.name} and I am {this.age} years old.";
        }
    } 
}

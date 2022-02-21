using System;

namespace definirane_klasove_obekti
{
    class Program
    {
        static void Main(string[] args)
        {
            Person person1 = new Person("Joshua", 26);
            Console.WriteLine(person1.IntroduceYourself());

            Person secondperson = new Person("Corey", 48);
            Console.WriteLine(secondperson.IntroduceYourself());

            Person thirdperson = new Person("Mick", 48);
            Console.WriteLine(thirdperson.IntroduceYourself());
            Console.ReadLine();
        }
    }
}

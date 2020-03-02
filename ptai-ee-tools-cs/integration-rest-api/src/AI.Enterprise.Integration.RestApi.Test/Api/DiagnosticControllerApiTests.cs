/* 
 * Api Documentation
 *
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 * 
 * Generated by: https://github.com/openapitools/openapi-generator.git
 */

using System;
using System.IO;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;
using RestSharp;
using NUnit.Framework;

using AI.Enterprise.Integration.RestApi.Client;
using AI.Enterprise.Integration.RestApi.Api;
using AI.Enterprise.Integration.RestApi.Model;

namespace AI.Enterprise.Integration.RestApi.Test
{
    /// <summary>
    ///  Class for testing DiagnosticControllerApi
    /// </summary>
    /// <remarks>
    /// This file is automatically generated by OpenAPI Generator (https://openapi-generator.tech).
    /// Please update the test case below to test the API endpoint.
    /// </remarks>
    public class DiagnosticControllerApiTests
    {
        private DiagnosticControllerApi instance;

        /// <summary>
        /// Setup before each unit test
        /// </summary>
        [SetUp]
        public void Init()
        {
            instance = new DiagnosticControllerApi();
        }

        /// <summary>
        /// Clean up after each unit test
        /// </summary>
        [TearDown]
        public void Cleanup()
        {

        }

        /// <summary>
        /// Test an instance of DiagnosticControllerApi
        /// </summary>
        [Test]
        public void InstanceTest()
        {
            // TODO uncomment below to test 'IsInstanceOf' DiagnosticControllerApi
            //Assert.IsInstanceOf(typeof(DiagnosticControllerApi), instance);
        }

        
        /// <summary>
        /// Test GetComponentsStatusUsingGET
        /// </summary>
        [Test]
        public void GetComponentsStatusUsingGETTest()
        {
            // TODO uncomment below to test the method and replace null with proper value
            //var response = instance.GetComponentsStatusUsingGET();
            //Assert.IsInstanceOf(typeof(ComponentsStatus), response, "response is ComponentsStatus");
        }
        
        /// <summary>
        /// Test GetProject
        /// </summary>
        [Test]
        public void GetProjectTest()
        {
            // TODO uncomment below to test the method and replace null with proper value
            //string projectName = null;
            //var response = instance.GetProject(projectName);
            //Assert.IsInstanceOf(typeof(Guid), response, "response is Guid");
        }
        
    }

}